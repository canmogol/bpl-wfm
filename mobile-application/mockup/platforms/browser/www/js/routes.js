angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    

      .state('menu.konumlar', {
    url: '/locations',
    views: {
      'side-menu21': {
        templateUrl: 'templates/konumlar.html',
        controller: 'konumlarCtrl'
      }
    }
  })

  .state('giris', {
    url: '/login',
    templateUrl: 'templates/giris.html',
    controller: 'girisCtrl'
  })

  .state('menu.mesajlar', {
    url: '/messages',
    views: {
      'side-menu21': {
        templateUrl: 'templates/mesajlar.html',
        controller: 'mesajlarCtrl'
      }
    }
  })

  .state('menu.ayarlar', {
    url: '/settings',
    views: {
      'side-menu21': {
        templateUrl: 'templates/ayarlar.html',
        controller: 'ayarlarCtrl'
      }
    }
  })

  .state('menu', {
    url: '/side-menu',
    templateUrl: 'templates/menu.html',
    controller: 'menuCtrl'
  })

  .state('menu.mesajlar2', {
    url: '/chat',
    views: {
      'side-menu21': {
        templateUrl: 'templates/mesajlar2.html',
        controller: 'mesajlar2Ctrl'
      }
    }
  })

$urlRouterProvider.otherwise('/login')


});