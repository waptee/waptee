angular.module('waptee.controllers', []).
controller('topicsController', function($scope, $routeParams, topicsService) {

    $scope.topicsList = [];

    topicsService.getTopics().success(function (response) {
        $scope.topicsList = response; 
    });
    
}).config(
 ['$routeProvider', function($routeProvider) {
  $routeProvider.
	when('/topics', {templateUrl: '/app/topics/topicsView.html', controller: 'topicsController'}).
	otherwise({redirectTo: '/'});
}]);