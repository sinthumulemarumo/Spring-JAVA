
var app = angular.module('myapp',['ngRoute','angular-storage']);
    app.config(function($routeProvider,$locationProvider) {
    
        $routeProvider
        .when("/", {
            templateUrl : '/home.html'
        }).when("/register", {
            templateUrl : "/register.html",
            controller : "registerController"
        }).when("/login",{
            templateUrl : "/login.html",
            controller : "loginController"
        }).when("/products",{
            templateUrl : "/products.html",
            controller : "productController"
        }).when("/admin",{
            templateUrl : "/admin.html",
            controller : "adminController"
        }).when("/addProduct",{
            templateUrl : "/addProduct.html",
            controller : "productController"
        }).when("/cart",{
             templateUrl : "/cart.html",
             controller : "productController"
        }).when("/adminProducts",{
            templateUrl : "/adminProducts",
            controller : "productController"
        }).when("/customersList",{
            templateUrl : "/customersList",
            controller : "customersController"
        }).when("/checkout",{
            templateUrl : "/checkout.html",
            controller : "productController"
            
        }).otherwise({
            redirectTo : ("/")
        });
        
      $locationProvider.html5Mode(false);
     
 
    });
app.controller('registerController',function($scope,$http,$location,$rootScope){
   
        $scope.register =  function(){
            var templateUrl =  "/register";
            var customer = $scope.customer;
            $http.post(templateUrl,customer).then(successCallback,errorCallback);
            function successCallback(response){
                $rootScope.customer = customer;
                $location.url("/login");
                alert(customer.firstname + " "+"You have Successfully Registered, Click OK to login");
            }
            function errorCallback(response){
                $location.url("/register");
                 alert(response.data.status);
            }
        };
     
        
    });
    
app.controller('loginController',function($http,$scope,$location,store,$rootScope){
    $scope.login = function(){
//        alert("login Controller active");
        $http({
            url: '/login/user',
            method: 'GET',
            params: {username: $scope.username , password: $scope.password}
        }).then(successCallback, errorCallback);
            function successCallback(respose){
                var customer = respose.data;
                localStorage.setItem("customer",JSON.stringify(customer.id));
                 
//                store.set("UserLoggedIn",customer.username);
                if(customer.username==="admin" ){
                    alert("Successfully Logged In");
                   ;
                    $location.url('/admin');
                }else{
                    if($scope.username === customer.username){
                        alert("Successfully Logged In");
                        $location.url('/products');
                    }else{
                        alert("Invalid Credentials,Please try again");
                        $location.url('/login');
                    }
                } 
            }; 
            function errorCallback(response){
                console.log(response);
                alert("Invalid Credentials,Please try again");
                $location.url('/login');
            };
      };
 
   
      
  });  
app.controller('adminController',function($http,$scope){
     $http.get("/customers/all").then(function(response){
            $scope.customers = response.data;
    });  
    
    $http.get("/products/all").then(function(response){
            $scope.products = response.data;
    }); 
        
}) ; 
app.controller('logoutController',function($location,$session){
   $session.clear();
   $location.url("/login");
});  


app.controller('productController',function($http,$scope,$location,$rootScope,$filter){
  //alert("product.js Controller Activated");
    $scope.image = null;
    $http.defaults.headers.post["Content-Type"] = "application/json";
    var url = '/addProduct';
    var imageCopy = null;
    var image = null;
    var handleImageSelect = function (evt)
    {
        var files = evt.target.files;
        var file = files[0];
        if (files && file){
            var reader = new FileReader();
            reader.onload = function (readerEvt) {
                var binaryString = readerEvt.target.result;
                imageCopy = btoa(binaryString);
                image = 'data:image/octet-stream;base64,' + imageCopy;
                $scope.image = image;
            };
        reader.readAsBinaryString(file);
        }
    };
//    if (window.File && window.FileReader && window.FileList && window.Blob) {
//       document.getElementById('filePickerImage').addEventListener('change', handleImageSelect, false);
//    } else {
//        alert('The File APIs are not fully supported in this browser.');
//    }
    $scope.saveProd = function (){
        $http.post(url, {
            name:  $scope.name,
            description: $scope.description,
            price: $scope.price,
            image: $scope.image
          

        }).then(successCallback, errorCallback);
           function successCallback(response) {
                alert("Product Added Successfully");
                console.log(response.data);
                $location.url('/admin');
            }
            function errorCallback(response) {
                console.log(response);
                alert("failed to add Product");
            };        
    };
    
    /**Retrieving Products from the Database**/
    $http.get("/products/all").then(function(response){
            $scope.products = response.data;
    });  
    
    
    $scope.cart = [];
    $scope.total = 0;
    var quantity = $scope.quantity;
    $scope.addToCart = function(prod){
        if($scope.cart.length === 0){
            prod.quantity = 1;
            $scope.cart.push(prod);
           
//            console.log($scope.cart);
        }else{
            for(var i = 0 ; i < $scope.cart.length;i++){
                if($scope.cart[i].product_id === prod.product_id){
                   alert("Product Already Added,You can update its Quantity on your list");
                    break;
                }else{
                    //alert("not found in cart list");
                    $scope.cart.push(prod);
                    prod.quantity = 1;
                   
                    break;
                }
            }
        }
        $scope.total +=parseFloat(prod.price);
       
        localStorage.setItem("quantity",JSON.stringify(prod.quantity));
        localStorage.setItem("total",JSON.stringify($scope.total));
//        console.log( localStorage.getItem("cartData"));
       
    };
    
   
    $scope.removeProdCart = function(prod){
        if(prod.quantity > 1){
            prod.quantity -= 1;
             localStorage.setItem("quantity",JSON.stringify(prod.quantity));
        }else if(prod.quantity === 1){
            var index = $scope.cart.indexOf(prod);
            $scope.cart.splice(index, 1);
 	}
        $scope.total -= parseFloat(prod.price);
      
        localStorage.setItem("total",JSON.stringify($scope.total));
    };
    $scope.incrementQuantity = function(prod){
        prod.quantity +=1;
        $scope.total +=parseFloat(prod.price);
        
        localStorage.setItem("total",JSON.stringify($scope.total));
        localStorage.setItem("quantity",JSON.stringify(prod.quantity));
    };
    
    $scope.goToCheckoutPage = function(){
       $location.url('/checkout');
    };
    
  
    $scope.placeOrder = function(){
     
       var orderData = {
           'custId': localStorage.getItem("customer"),
           'price' : localStorage.getItem("total"),
           'productCopy': localStorage.getItem("cartData"),
           'bankDetailId': localStorage.getItem("bankDetail"),
           'quantity' : localStorage.getItem("quantity"),
           'status' : 'successful', 
           'orderedDate': $filter('date')(new Date(), 'yyyy-MM-dd')
           
       };
       console.log(orderData);
       
       $http({
            url: '/placeOrder',
            method: "POST",
            data:orderData,
            headers: {
           'Content-Type': 'application/json;charset=utf-8;'
       }
       }).then(successCallback, errorCallback);
       function successCallback(response) {
           alert("Congradulations ,Your Order has been placed Successfully ");
        $rootScope.order = response.data;  
        }
        function errorCallback(response) {
            alert("failed");
            console.log(response.data);
        }; 

    };
    
    $scope.makePayment = function(){
       $http({
            url :'/pay',
            method : 'GET',
            params : {
                'bankType' : $scope.bank.bankType,
                'accNo' : $scope.bank.accNo,
                'pin' : $scope.bank.pin
            }
        }).then(successCallBack,errorCallBack);
        function successCallBack(response){
           if($scope.bankType === bankType ){
               localStorage.setItem("bankDetail",JSON.stringify($scope.bank.id));
               console.log( localStorage.getItem("bankDetail"));
           };
        };
        function errorCallBack (){
            
        };
    };
    
    $scope.checkOut = function (){
        $rootScope.itemsNum = $rootScope.cartItems.length;
        $scope.placeOrder();
       
    };
    
    
});


