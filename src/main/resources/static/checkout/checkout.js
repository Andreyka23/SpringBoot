angular.module('app').controller('checkoutController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.cart = response.data;
            });
    };

    $scope.submitCheckout = function () {
        $http.post(contextPath + '/api/v1/orders', {'username': $scope.username, 'phone': $scope.phone, 'address': $scope.address})
            .then(function (response) {
                alert('Добавлен новый заказ');
            });
    };

    $scope.cartContentRequest();
});