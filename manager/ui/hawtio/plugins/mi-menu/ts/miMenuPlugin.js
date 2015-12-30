/// Copyright 2014-2015 Red Hat, Inc. and/or its affiliates
/// and other contributors as indicated by the @author tags.
///
/// Licensed under the Apache License, Version 2.0 (the "License");
/// you may not use this file except in compliance with the License.
/// You may obtain a copy of the License at
///
///   http://www.apache.org/licenses/LICENSE-2.0
///
/// Unless required by applicable law or agreed to in writing, software
/// distributed under the License is distributed on an "AS IS" BASIS,
/// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
/// See the License for the specific language governing permissions and
/// limitations under the License.
/// <reference path="../../includes.ts"/>
/// <reference path="miMenuGlobals.ts"/>
var MiMenu;
(function (MiMenu) {
    MiMenu._module = angular.module(MiMenu.pluginName, []);
    var tab = undefined;
    MiMenu._module.config(["$locationProvider", "$routeProvider", "HawtioNavBuilderProvider", function ($locationProvider, $routeProvider, builder) {
        tab = builder.create().id(MiMenu.pluginName).title(function () { return "MiMenu"; }).href(function () { return "/miMenu"; }).subPath("Mi pagina", "miPagina", builder.join(MiMenu.templatePath, "home.html")).build();
        builder.configureRouting($routeProvider, tab);
        $locationProvider.html5Mode(true);
    }]);
    MiMenu._module.run(["HawtioNav", function (HawtioNav) {
        HawtioNav.add(tab);
        MiMenu.log.debug("loaded");
    }]);
    hawtioPluginLoader.addModule(MiMenu.pluginName);
})(MiMenu || (MiMenu = {}));
//# sourceMappingURL=miMenuPlugin.js.map