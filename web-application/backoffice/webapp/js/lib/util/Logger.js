/**
 * @instance {Logger} Logger
 */
var Logger = (function () {

    /**
     * default implementation of Logger interface, using global console
     * @constructor
     */
    function LoggerImpl() {

        function pad(width, string, padding) {
            return (width <= string.length) ? string : pad(width, string + padding, padding)
        }

        function prefix(level, log) {
            try {
                throw new Error(arguments);
            } catch (e) {
                var stack = e.stack.split('\n');
                if (stack.length > 3) {
                    var callSiteSplit = stack[4].trim().split('/');
                    if (callSiteSplit.length > 0) {
                        var callSite = callSiteSplit[callSiteSplit.length - 1];
                        if (callSite[callSite.length - 1] === ')') {
                            callSite = callSite.substring(0, callSite.length - 1);
                        }
                        log = pad(6, level, " ") + " " + pad(30, callSite, " ") + " " + log;
                    }
                }
            }
            return log;
        }

        this.log = function (log) {
            console.log(prefix('LOG', log));
        };
        this.debug = function (log) {
            console.debug(prefix('DEBUG', log));
        };
        this.error = function (log) {
            console.error(prefix('ERROR', log));
        };
        this.info = function (log) {
            console.info(prefix('INFO', log));
        };
    }

    /**
     * instance variable
     * @private
     */
    var _instance;

    /**
     * @type {Logger} Logger
     */
    return {

        /**
         * default log method
         * @param {string} log
         */
        log: function (log) {
            this.getInstance().log(log);
        },

        /**
         * debug logging
         * @param {string} log
         */
        debug: function (log) {
            this.getInstance().debug(log);
        },

        /**
         * error logging
         * @param {string} log
         */
        error: function (log) {
            this.getInstance().error(log);
        },

        /**
         * info logging
         * @param {string} log
         */
        info: function (log) {
            this.getInstance().info(log);
        },

        /**
         * singleton Logger
         * @returns {Logger} Logger
         */
        getInstance: function () {
            if (!_instance) {
                _instance = new LoggerImpl();
            }
            return _instance;
        }
    };

})();
