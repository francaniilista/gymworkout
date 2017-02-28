'use strict';

/* App Module */

var gymworkoutApp = angular.module('gymworkoutApp', ['ngRoute', 'ngResource','ui.bootstrap', 'nvd3']);

gymworkoutApp.config(function($routeProvider) {
	$routeProvider.
		when('/', {
			templateUrl: 'js/views/welcome.html'
		}).
		when('/trainingPlan', {
			templateUrl: 'js/views/training-plan-form.html'
		}).
		when('/dworkout', {
			templateUrl: 'js/views/workout-form.html'
		}).
		otherwise({
			redirectTo: '/'
		});
	});