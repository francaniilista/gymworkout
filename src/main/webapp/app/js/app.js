'use strict';

/* App Module */

var gymworkoutApp = angular.module('gymworkoutApp', ['ngRoute',
	'ui.bootstrap'
]);

gymworkoutApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/', {
				templateUrl: 'js/views/welcome.html',
        		controller: 'WelcomeCtrl'
      		}).
      		when('/dworkout', {
      			templateUrl: 'js/views/workout-form.html',
        		controller: 'WorkoutFormCtrl'
      		}).
      		otherwise({
      			redirectTo: '/'
      		});
	}
]);