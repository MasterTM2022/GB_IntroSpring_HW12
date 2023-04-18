let app = angular.module('app', []);
app.value('contextPath', 'http://localhost:8180/app');

app.controller('productController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {

        $scope.fillProductsTable = function () {
            $http.get(contextPath + '/all-products')
                .then(function (response) {
                    $scope.ProductsList = response.data;
                });
            $scope.minMaxCost();
        };

        $scope.addProduct = function () {
            if (confirm("Do you want to add product «" + document.getElementsByName("titleNew")[0].value +
                "» with cost " + document.getElementsByName("costNew")[0].value + "?")) {
                $http({
                    url: contextPath + '/add',
                    method: 'post',
                    params: {
                        title: document.getElementsByName("titleNew")[0].value,
                        cost: document.getElementsByName("costNew")[0].value
                    }
                }).then(function () {
                    $scope.fillProductsTable();
                    $('#addProduct')[0].reset();
                    alert("Added");
                });
            } else {
                alert("Canceled");
            }
        }

        $scope.minMaxCost = function () {
            $http.get(contextPath + '/product/min-max')
                .then(function (response) {
                        $scope.costMin = response.data[0];
                        $scope.costMax = response.data[1];
                        document.getElementsByName("costMin")[0].value = $scope.costMin;
                        document.getElementsByName("costMax")[0].value = $scope.costMax;
                    }
                )
        }

        $scope.reset = function () {
            $scope.fillProductsTable();
            $scope.minMaxCost();
        }

        $scope.deleteProductById = function (productId, productTitle, productCost) {
            if (confirm("Do you want to delete product «" + productTitle + "» with ID=" + productId + " and Cost=" + productCost + "?")) {
                $http.get(contextPath + '/delete/' + productId)
                    .then(function (response) {
                            if (response.status === 204) {
                                alert("Deleted");
                                $scope.fillProductsTable();
                            }
                        }
                    ).catch((error) => {
                        if (error.status === 409) {
                            alert("Product with id=" + productId + " can not be deleted from DataBase, it are used in orders.");
                        }
                        if (error.status === 404) {
                            alert("Product with id=" + productId + " was not found in DataBase");
                            $scope.fillProductsTable();
                        }

                    }
                )
            } else {
                alert("Canceled");
            }
        }

        $scope.applyFilter = function () {
            $scope.costMin = document.getElementsByName("costMin")[0].value;
            $scope.costMax = document.getElementsByName("costMax")[0].value;
            $http({
                url: contextPath + '/product/filtered',
                method: "get",
                params: {
                    costMin: $scope.costMin,
                    costMax: $scope.costMax
                }
            }).then(function (response) {
                $scope.ProductsList = response.data;
            })

        }

        $scope.findProduct = function () {
            if (confirm("Do you want to find product with ID=" + document.getElementsByName("finderId")[0].value + "?")) {
                $http.get(contextPath + '/product/' + document.getElementsByName("finderId")[0].value)
                    .then(function (response) {
                        if (response.data.length === 0) { //response.data.length&&response.data[0] === null
                            alert("No product with ID=" + document.getElementsByName("finderId")[0].value)
                            $scope.fillProductsTable();
                            document.getElementsByName("finderId").value = ''
                        } else {
                            alert("Found");
                            document.getElementsByName("finderId")[0].value = "";
                            $scope.ProductsList = response.data;
                        }
                    });
            } else {
                alert("Canceled");
            }
        }

        $scope.fillProductsTable();
    }
    ]
)

app.controller('customerController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {
        // const contextPath = 'http://localhost:8180/app';

        $scope.fillCustomersTable = function () {
            $http.get(contextPath + '/all-customers')
                .then(function (responseCustomers) {
                    $scope.CustomersList = responseCustomers.data;
                });
        };

        $scope.fillCustomersTable();

    }]
)