/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('gameApp', ['angucomplete-alt', 'angularUtils.directives.dirPagination']);
        app.controller('gameController',
                function ($scope, $http) {

                $scope.init = function () {
                $scope.search_by = "name";
                };
                        $scope.getTypeahead = function () {
                        $http({
                        method: 'GET',
                                url: 'TypeaheadServlet',
                                params: {'searchBy': $scope.search_by, 'searchItem': $scope.search_item}
                        }).success(function (data, status, headers, config) {
                        $scope.array_ahead = data;
                        }).error(function (data, status, headers, config) {
                        alert("server response error: " + data + " \nstatus: " + status);
                        });
                        };
                        $scope.inputChanged = function (str) {
                        $scope.search_item = str;
                                console.log('inputChanged by ' + $scope.search_by + ' ' + $scope.searchItem);
                                $scope.getTypeahead();
                        };
                        $scope.getDataFromServer = function () {
                        $http({
                        method: 'GET',
                                url: 'AngularJsServlet',
                                params: {'searchBy': $scope.search_by, 'searchItem': $scope.search_item}
                        }).success(function (data, status, headers, config) {
                        $scope.names = data;
                        }).error(function (data, status, headers, config) {
                        alert("server response error: " + data + " \nstatus: " + status);
                        });
                        };
                        $scope.selectedItem = function (selected) {
                        $scope.search_item = selected.title;
                                console.log('selectedItem by' + $scope.search_by + ' ' + $scope.search_item);
                                $scope.getDataFromServer();
                        };
                        $scope.init();
                });
     