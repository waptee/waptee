'use strict';

var module = angular.module('waptee.services', []);

module.factory('backend',
	    function ($http, $log) {
	        var jsonTransform = function (data, headers) {
	            return angular.fromJson(data);
	        };
	        var service = {
	            user:function () {
	                return $http.get('/user', {transformResponse:jsonTransform});
	            },
	            save:function ($scope) {
	                $log.info('Saving');
	                return $http({
	                    url:'/svc',
	                    params: {
	                    	firstName: $scope.firstName,
	                    	lastName: $scope.lastName,
	                    	nickname: $scope.nickname,
	                    	birthday: $scope.nickname
	                    },
	                    method: 'POST',
	                    headers:{
	                        'Content-Type':'application/json'
	                    }
	                });
	            }
	        };
	        return service;
	    });
