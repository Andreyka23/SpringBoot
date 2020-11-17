angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';


    $scope.submitUpdateProfile = function () {
        $http({
            url: contextPath + '/api/v1/users',
            method: 'PUT',
            params: $scope.user
        })
            .catch(function (response) {
                if (typeof response.data !== 'undefined')
                    alert(response.data.message);
                else
                    alert('Профиль обновлен!');
            });
    };

    $scope.getUser = function () {
        $http({
            url: contextPath + '/api/v1/users',
            method: 'GET'
        })
            .then(function (response) {
                $scope.user = response.data;
            });
    };

    $scope.getUser();
});