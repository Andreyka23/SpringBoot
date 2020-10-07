angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data.content;
                $scope.Page.first = response.data.first;
                $scope.Page.last = response.data.last;
                $scope.Page.number = response.data.number;
            });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.findProducts = function () {
         $http({
             url: contextPath + '/api/v1/products',
             method: "GET",
             params: $scope.findProduct
         }).then(function (response) {
                $scope.Products = response.data.content;
                $scope.Page.first = response.data.first;
                $scope.Page.last = response.data.last;
                $scope.Page.number = response.data.number;
         });
    };

    $scope.goToPage = function (page) {
         $http({
             url: contextPath + '/api/v1/products?'+page,
             method: "GET",
             params: $scope.findProduct
         }).then(function (response) {
                $scope.Products = response.data.content;
                $scope.Page.first = response.data.first;
                $scope.Page.last = response.data.last;
                $scope.Page.number = response.data.number;
         });
    };

    $scope.fillTable();
});