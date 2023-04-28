let app = angular.module('app', []);
app.value('contextPath', 'http://localhost:8180/app/api/v1');

app.controller('productController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {

        // $scope.fillProductsTable = function () {
        //     $http.get(contextPath + '/products')
        //         .then(function (response) {
        //             $scope.ProductsList = response.data;
        //         });
        //     $scope.minMaxPrice();
        // };

        $scope.addProduct = function () {
            if (confirm("Do you want to add product «" + document.getElementsByName("titleNew")[0].value +
                "» with price " + document.getElementsByName("priceNew")[0].value + "?")) {
                $http({
                    url: contextPath + '/products',
                    method: 'post',
                    params: {
                        title: document.getElementsByName("titleNew")[0].value,
                        price: document.getElementsByName("priceNew")[0].value
                    }
                }).then(function () {
                    $scope.applyFilter();
                    $('#addProduct')[0].reset();
                    alert("Added");
                });
            } else {
                alert("Canceled");
            }
        }

        $scope.minMaxPrice = function () {
            $http.get(contextPath + '/products/' + 'min-max')
                .then(function (response) {
                        $scope.priceMin = response.data[0];
                        $scope.priceMax = response.data[1];
                        document.getElementsByName("priceMin")[0].value = $scope.priceMin;
                        document.getElementsByName("priceMax")[0].value = $scope.priceMax;
                    }
                )
        }

        $scope.reset = function () {
            $scope.minMaxPrice();
            document.getElementsByName("id")[0].value = "";
            document.getElementsByName("partTitle")[0].value = "";
            $scope.id = document.getElementsByName("id")[0].value;
            $scope.applyFilter(1);
        }

        $scope.addToCart = function (productId, productTitle, productPrice) {
            if (confirm("Do you want to add product «" + productTitle + "» with ID=" + productId + " and Price=" + productPrice + " to the cart?")) {
                $http.get(contextPath + '/products/' + productId + "/add_to_cart")
                    .then(function (response) {
                            if (response.status === 200) {
                                alert("Added");
                                $scope.reset();
                                $scope.applyFilter();
                            }
                        }
                    ).catch((error) => {
                        if (error.status === 409) {
                            alert("Product with id=" + productId + " can not be added to the cart.");
                        }
                        if (error.status === 404) {
                            alert("Product with id=" + productId + " was not found in DataBase");
                            $scope.reset();
                            $scope.applyFilter();
                        }

                    }
                )
            } else {
                alert("Canceled");
            }
        }

        $scope.deleteProductById = function (productId, productTitle, productPrice) {
            if (confirm("Do you want to delete product «" + productTitle + "» with ID=" + productId + " and Price=" + productPrice + "?")) {
                $http.delete(contextPath + '/products/' + productId)
                    .then(function (response) {
                            if (response.status === 204) {
                                alert("Deleted");
                                $scope.reset();
                                $scope.applyFilter();
                            }
                        }
                    ).catch((error) => {
                        if (error.status === 409) {
                            alert("Product with id=" + productId + " can not be deleted from DataBase, it are used in orders.");
                        }
                        if (error.status === 404) {
                            alert("Product with id=" + productId + " was not found in DataBase");
                            $scope.reset();
                            $scope.applyFilter();
                        }

                    }
                )
            } else {
                alert("Canceled");
            }
        }

        $scope.applyFilter = function (pageIndex = 1) {
            $scope.id = document.getElementsByName("id")[0].value;
            $scope.priceMin = document.getElementsByName("priceMin")[0].value;
            $scope.priceMax = document.getElementsByName("priceMax")[0].value;
            $scope.partTitle = document.getElementsByName("partTitle")[0].value;
            $http({
                url: contextPath + '/products',
                method: "get",
                params: {
                    id: $scope.id ? $scope.id : null,
                    minPrice: $scope.priceMin ? $scope.priceMin : null,
                    maxPrice: $scope.priceMax ? $scope.priceMax : null,
                    partTitle: $scope.partTitle ? $scope.partTitle : null,
                    pageIndex: $scope.pageIndex ? $scope.pageIndex : 1
                }
            }).then(function (response) {
                $scope.ProductsList = response.data.content;
            })

        }

        $scope.findProduct = function () {
            if (confirm("Do you want to find product with ID=" + document.getElementsByName("finderId")[0].value + "?")) {
                $http.get(contextPath + '/products/' + document.getElementsByName("finderId")[0].value)
                    .then(function (response) {
                        if (response.data.length === 0) { //response.data.length&&response.data[0] === null
                            alert("No product with ID=" + document.getElementsByName("finderId")[0].value)
                            $scope.applyFilter();
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
        $scope.minMaxPrice();
        $scope.applyFilter();
    }
    ]
)

app.controller('customerController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {
        // const contextPath = 'http://localhost:8180/app';

        $scope.fillCustomersTable = function () {
            $http.get(contextPath + '/customers')
                .then(function (responseCustomers) {
                    $scope.CustomersList = responseCustomers.data;
                    console.log(responseCustomers.data);
                });
        };

        $scope.fillCustomersTable();

    }]
)

app.controller('cartController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {
        // const contextPath = 'http://localhost:8180/app';

        $scope.fillCartTable = function () {
            $http.get(contextPath + '/cart')
                .then(function (responseCart) {
                    $scope.CartList = responseCart.data;
                    console.log(responseCart.data);
                });
        };

        $scope.fillCartTable();

    }]
)

app.controller('startController', ['$scope', '$http', 'contextPath', function ($scope, $http, contextPath) {
        // const contextPath = 'http://localhost:8180/app';


    $scope.login = function () {
        $http({
            url: '/app/auth/token',
            method: 'post',
            params: {
                username: "user",
                password: "pass"
            }
        })
    };

    $scope.login();
    }]
)