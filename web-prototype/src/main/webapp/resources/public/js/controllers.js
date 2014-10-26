'use strict';

function UserCtrl($scope, backend) {
    $scope.user = null;
    $scope.login = function () {
        backend.user().then(function (response) {
            $scope.user = response.data;
        });
    }
    $scope.login();
}

function FileCtrl($scope, backend) {
	$scope.save = function() {
		backend.save($scope).then(function (response) {
        });
	}
	$scope.load = function() {
		backend.load($scope).then(function (response) {
			var result = JSON.parse(response.data.content);
			$scope.firstName = result.firstName;
			$scope.lastName = result.lastName;
			$scope.nickname = result.nickname;
			$scope.birthday = result.birthday;
		});
	}
}