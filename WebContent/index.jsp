<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Search Gamers into club portal  with Servlets using AngularJS</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/gclub.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/angular.min.js"></script>
        <script type="text/javascript" src="js/angucomplete-alt.min.js"></script>
        <script type="text/javascript" src="js/dirPagination.js" ></script>
        <script type="text/javascript" src="js/gmclub.js"></script>
    </head>
    <body>
        <!--
        <style>
            body {
                background-color: #FFFF96;
            }
        </style>
        -->
        <div align="center" data-ng-app="gameApp" style="">

            <div ng-controller="gameController">
                Search by 
                <select  ng-model="search_by" id="search_by" name="search_by">
                    <option value="name">name</option>
                    <option value="email">email</option>
                    <option value="game">game</option>
                </select>


                <div
                    angucomplete-alt
                    id="names"
                    class="angucomplete-holder"
                    placeholder="search text"
                    maxlength="100"
                    pause="100"
                    selected-object="selectedItem"
                    local-data="array_ahead"
                    search-fields="name"
                    title-field="name"
                    minlength="1"
                    input-class="form-control form-control-small"
                    match-class="highlight"
                    input-changed="inputChanged"
                    clear-selected="true"
                    >
                </div>
                <div ng-show="selectedItem" id="searchItem" name="searchItem" >
                    <b>You search</b>: {{search_item}}
                    <input type="image" ng-click="getDataFromServer()" align="center" src="img/search.jpg" alt="search" height="32" width="32" /> 
                </div>
                <br>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <h4 href="#" >
                                    avatar
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" ng-click="orderByField = 'name';
                                            reverseSort = !reverseSort">
                                    name <span ng-show="orderByField == 'name'"><span ng-show="!reverseSort" >&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" ng-click="orderByField = 'joined';
                                            reverseSort = !reverseSort">
                                    joined <span ng-show="orderByField == 'joined'"><span ng-show="!reverseSort" >&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>


                            <th>
                                <h4 href="#" ng-click="orderByField = 'game';
                                            reverseSort = !reverseSort">
                                    game <span ng-show="orderByField == 'game'"><span ng-show="!reverseSort">&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" ng-click="orderByField = 'result';
                                            reverseSort = !reverseSort">
                                    result <span ng-show="orderByField == 'result'"><span ng-show="!reverseSort">&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" ng-click="orderByField = 'email';
                                            reverseSort = !reverseSort">
                                    email <span ng-show="orderByField == 'email'"><span ng-show="!reverseSort">&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" ng-click="orderByField = 'state';
                                            reverseSort = !reverseSort">
                                    state <span ng-show="orderByField == 'state'"><span ng-show="!reverseSort">&uarr;</span><span ng-show="reverseSort">&darr;</span></span>
                                </h4>
                            </th>

                            <th>
                                <h4 href="#" >
                                    flag
                                </h4>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--tr dir-paginate="x in names | itemsPerPage:8 "-->
                        <tr dir-paginate= "x in names | orderBy:orderByField:reverseSort| itemsPerPage:8 ">
                            <td><img src="{{ x.avatar}}" alt="picture" height="32" width="32"></td>
                            <td>{{ x.name}}</td>
                            <td>{{ x.joined}}</td>
                            <td>{{ x.game | uppercase }}</td>
                            <td>{{ x.result}}</td>
                            <td>{{ x.email}}</td>
                            <td>{{x.state}}</td>
                            <td><img src="{{ x.flag}}" alt="picture" height="32" width="32"></td>
                        </tr>
                    </tbody>
                </table>
                <dir-pagination-controls
                    max-size="8"
                    direction-links="true"
                    boundary-links="true" >
                </dir-pagination-controls>
            </div>
        </div>
    </body>
</html>