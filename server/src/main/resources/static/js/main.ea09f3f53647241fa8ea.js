webpackJsonp([6],{

/***/ "./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/common/common.vue":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";

const version  = "1.0.2.20190401"
let selectedBooks = []
let userName = undefined
let highlightBook = undefined
const MAX_NUMBER = 4

/* harmony default export */ __webpack_exports__["a"] = ({
  version,
  selectedBooks,
  highlightBook,
  MAX_NUMBER,
  userName
});


/***/ }),

/***/ "./src/common/common.vue":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__node_modules_vue_loader_lib_selector_type_script_index_0_common_vue__ = __webpack_require__("./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/common/common.vue");
/* unused harmony namespace reexport */
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__node_modules_vue_loader_lib_runtime_component_normalizer__ = __webpack_require__("./node_modules/vue-loader/lib/runtime/component-normalizer.js");
var disposed = false
/* script */


/* template */
var __vue_render__, __vue_static_render_fns__
/* template functional */
var __vue_template_functional__ = false
/* styles */
var __vue_styles__ = null
/* scopeId */
var __vue_scopeId__ = null
/* moduleIdentifier (server only) */
var __vue_module_identifier__ = null

var Component = Object(__WEBPACK_IMPORTED_MODULE_1__node_modules_vue_loader_lib_runtime_component_normalizer__["a" /* default */])(
  __WEBPACK_IMPORTED_MODULE_0__node_modules_vue_loader_lib_selector_type_script_index_0_common_vue__["a" /* default */],
  __vue_render__,
  __vue_static_render_fns__,
  __vue_template_functional__,
  __vue_styles__,
  __vue_scopeId__,
  __vue_module_identifier__
)
Component.options.__file = "src/common/common.vue"

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-3ad98006", Component.options)
  } else {
    hotAPI.reload("data-v-3ad98006", Component.options)
  }
  module.hot.dispose(function (data) {
    disposed = true
  })
})()}

/* harmony default export */ __webpack_exports__["a"] = (Component.exports);


/***/ }),

/***/ "./src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__("./node_modules/vue/dist/vue.esm.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_axios__ = __webpack_require__("./node_modules/vue-axios/dist/vue-axios.min.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue_axios__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue_material__ = __webpack_require__("./node_modules/vue-material/dist/vue-material.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_vue_material___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_vue_material__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__util_hot_reload__ = __webpack_require__("./src/util/hot-reload.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__router__ = __webpack_require__("./src/router.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__sass_main_scss__ = __webpack_require__("./src/sass/main.scss");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__sass_main_scss___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_5__sass_main_scss__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__common_common__ = __webpack_require__("./src/common/common.vue");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_axios__ = __webpack_require__("./node_modules/axios/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7_axios___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_7_axios__);






// @ts-ignore


__WEBPACK_IMPORTED_MODULE_0_vue__["default"].prototype.$axios = __WEBPACK_IMPORTED_MODULE_7_axios___default.a;
__WEBPACK_IMPORTED_MODULE_0_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_1_vue_axios___default.a, __WEBPACK_IMPORTED_MODULE_7_axios___default.a);
__WEBPACK_IMPORTED_MODULE_0_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_2_vue_material___default.a);
var navbarComponent = function () { return __webpack_require__.e/* import() */(0).then(__webpack_require__.bind(null, "./src/components/navbar/index.ts")).then(function (_a) {
    var NavbarComponent = _a.NavbarComponent;
    return NavbarComponent;
}); };
if (false) {
    var navbarModuleId_1 = './components/navbar';
    // first arguments for `module.hot.accept` and `require` methods have to be static strings
    // see https://github.com/webpack/webpack/issues/5668
    makeHot(navbarModuleId_1, navbarComponent, module.hot.accept('./components/navbar', function () { return reload(navbarModuleId_1, require('./components/navbar').NavbarComponent); }));
}
// tslint:disable-next-line:no-unused-expression
new __WEBPACK_IMPORTED_MODULE_0_vue__["default"]({
    el: '#app-main',
    router: Object(__WEBPACK_IMPORTED_MODULE_4__router__["a" /* createRouter */])(),
    data: {
        menuVisible: false
    },
    components: {
        'navbar': navbarComponent
    }
});


/***/ }),

/***/ "./src/router.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export createRoutes */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return createRouter; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__("./node_modules/vue/dist/vue.esm.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_router__ = __webpack_require__("./node_modules/vue-router/dist/vue-router.esm.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__util_hot_reload__ = __webpack_require__("./src/util/hot-reload.ts");



var homeComponent = function () { return __webpack_require__.e/* import() */(1).then(__webpack_require__.bind(null, "./src/components/home/index.ts")).then(function (_a) {
    var HomeComponent = _a.HomeComponent;
    return HomeComponent;
}); };
var aboutComponent = function () { return __webpack_require__.e/* import() */(2).then(__webpack_require__.bind(null, "./src/components/about/index.ts")).then(function (_a) {
    var AboutComponent = _a.AboutComponent;
    return AboutComponent;
}); };
var listComponent = function () { return __webpack_require__.e/* import() */(4).then(__webpack_require__.bind(null, "./src/components/list/index.ts")).then(function (_a) {
    var ListComponent = _a.ListComponent;
    return ListComponent;
}); };
var registerComponent = function () { return __webpack_require__.e/* import() */(3).then(__webpack_require__.bind(null, "./src/components/register/index.ts")).then(function (_a) {
    var RegisterComponent = _a.RegisterComponent;
    return RegisterComponent;
}); };
// const homeComponent = () => import(/* webpackChunkName: 'home' */'./components/home').then(({ HomeComponent }) => HomeComponent)
// const aboutComponent = () => import(/* webpackChunkName: 'about' */'./components/about').then(({ AboutComponent }) => AboutComponent)
// const listComponent = () => import(/* webpackChunkName: 'list' */'./components/list').then(({ ListComponent }) => ListComponent)
if (false) {
    var homeModuleId_1 = './components/home';
    var aboutModuleId_1 = './components/about';
    var listModuleId_1 = './components/list';
    var registerModuleId_1 = './components/register';
    // first arguments for `module.hot.accept` and `require` methods have to be static strings
    // see https://github.com/webpack/webpack/issues/5668
    makeHot(homeModuleId_1, homeComponent, module.hot.accept('./components/home', function () { return reload(homeModuleId_1, require('./components/home').HomeComponent); }));
    makeHot(aboutModuleId_1, aboutComponent, module.hot.accept('./components/about', function () { return reload(aboutModuleId_1, require('./components/about').AboutComponent); }));
    makeHot(listModuleId_1, listComponent, module.hot.accept('./components/list', function () { return reload(listModuleId_1, require('./components/list').ListComponent); }));
    makeHot(registerModuleId_1, registerComponent, module.hot.accept('./components/register', function () { return reload(registerModuleId_1, require('./components/register').RegsiterComponent); }));
}
__WEBPACK_IMPORTED_MODULE_0_vue__["default"].use(__WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]);
var createRoutes = function () { return [
    {
        path: '/',
        component: homeComponent
    },
    {
        path: '/about',
        component: aboutComponent
    },
    {
        path: '/list',
        component: listComponent
    },
    {
        path: '/register',
        component: registerComponent
    }
]; };
var createRouter = function () { return new __WEBPACK_IMPORTED_MODULE_1_vue_router__["a" /* default */]({ mode: 'history', routes: createRoutes() }); };


/***/ }),

/***/ "./src/sass/main.scss":
/***/ (function(module, exports) {

// removed by extract-text-webpack-plugin

/***/ }),

/***/ "./src/util/hot-reload.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export makeHot */
/* unused harmony export reload */
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue__ = __webpack_require__("./node_modules/vue/dist/vue.esm.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__ = __webpack_require__("./node_modules/vue-hot-reload-api/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__);
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : new P(function (resolve) { resolve(result.value); }).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = y[op[0] & 2 ? "return" : op[0] ? "throw" : "next"]) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [0, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};


function makeHot(id, componentLoader, acceptFunc) {
    return __awaiter(this, void 0, void 0, function () {
        var loadedComponent;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    if (true) return [3 /*break*/, 2];
                    __WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__["install"](__WEBPACK_IMPORTED_MODULE_0_vue__["default"]);
                    if (!__WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__["compatible"]) {
                        throw new Error('vue-hot-reload-api is not compatible with the version of Vue you are using.');
                    }
                    return [4 /*yield*/, componentLoader()];
                case 1:
                    loadedComponent = _a.sent();
                    __WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__["createRecord"](id, loadedComponent);
                    _a.label = 2;
                case 2: return [2 /*return*/];
            }
        });
    });
}
function reload(id, component) {
    __WEBPACK_IMPORTED_MODULE_1_vue_hot_reload_api__["reload"](id, component);
}


/***/ })

},["./src/main.ts"]);
//# sourceMappingURL=main.ea09f3f53647241fa8ea.js.map