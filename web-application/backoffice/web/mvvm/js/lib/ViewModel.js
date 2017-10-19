/**
 * @extends {LifeCycleAware}
 * @param {ViewModel} viewModel
 * @class {ViewModel} ViewModel
 */
function ViewModel(viewModel) {

    //
    // Private and public field declarations
    //

    /**
     * controller
     * @type {Controller}
     */
    var controller = null;

    //
    // Private and public method declarations
    //

    /**
     * lifecycle method start
     * @param {Controller} c
     */
    this.onStart = function (c) {
        controller = c;
    };

    /**
     * lifecycle method stop
     */
    this.onStop = function () {
        // for each property
        for (var property in viewModel) {
            if (viewModel.hasOwnProperty(property)
                && viewModel.isFunction(property) === false
                && viewModel.getBindings()[property] !== undefined) {
                // unwatch this property
                viewModel.unwatch(property);
            }
        }
    };

    /**
     * @return {Controller}
     */
    this.getController = function () {
        return controller;
    };


    //
    // i18n, bindings, actions and templates
    //

    /**
     * bindings mapping
     * @returns {{}}
     */
    this.getBindings = function () {
        throw new Error('unimplemented method');
    };

    /**
     * i18n mapping
     * @returns {{}}
     */
    this.getI18n = function () {
        throw new Error('unimplemented method');
    };

    /**
     * get actions
     * @returns {{}}
     */
    this.getActions = function () {
        throw new Error('unimplemented method');
    };

    /**
     * templates
     * @returns {{}}
     */
    this.getTemplates = function () {
        throw new Error('unimplemented method');
    };


    /**
     * assign value to element
     * @param element
     * @param value
     */
    function assignValue(element, value) {
        if (element.value !== undefined) {
            element.value = value;
        } else {
            element.innerHTML = value;
        }
    }

    /**
     * bidirectionally binds property and element's value
     * @param {string} property
     * @param {Object} element
     */
    function addBindings(property, element) {
        // assign property to element
        if (viewModel[property] !== null) {
            var value = viewModel[property];
            assignValue(element, value);
        }
        // otherwise if element has a 'value' assign it to property
        else if (element.value !== undefined) {
            viewModel[property] = element.value;
        }

        // add watch for changes on this property
        viewModel.watch(property, function (property, oldValue, newValue) {
            // if this property has any mappings, ex: 'username' -> 'usernameInput' element id
            // set UI element's value to new value of the property
            assignValue(element, newValue);
            // return new value, we won't change the value
            return newValue;
        });

        // for input elements we want to assign value right away
        if (element instanceof HTMLInputElement) {
            // add key-up event listeners to UI element since it is an input
            element.addEventListener('keyup', function () {
                viewModel[property] = element.value;
            });
        } else {
            // otherwise add value change listeners to UI element
            element.addEventListener('change', function () {
                viewModel[property] = element.value;
            });
        }
    }


    //
    // constructor
    //
    (function (self) {

        // extends LifeCycleAware
        self.extend(new LifeCycleAware());

        // for each property
        for (var property in viewModel) {
            // check if property is not inherited but defined in this class / created at this object
            // and if the property is not a function
            // and property has a binding
            // and the bound UI element exists
            if (viewModel.hasOwnProperty(property)
                && viewModel.isFunction(property) === false
                && viewModel.getBindings()[property] !== undefined
                && document.getElementById(viewModel.getBindings()[property]) !== null) {

                // find the UI element
                var elementId = viewModel.getBindings()[property];
                var element = document.getElementById(elementId);

                addBindings(property, element);
            }
        }

    })(this);


}
