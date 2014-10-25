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
            alert('salvo!');
        });
	}
}