var app = angular.module("ProjectCalculator", []);
 
// Controller Part
app.controller("TaskController", function($scope, $http) {
 
	$scope.taskList = [];
    $scope.taskForm = {
    	taskId: "",
    	taskDescription: "",
        taskDuration: "",
        taskStartDate:"",
        taskDepends: []
        
    };
    $scope.projectForm = {
    	projectDescription: "",
    	projectStartDate: "",
    	taskList: []
    };
    
     
	// HTTP POST/PUT methods for add/edit employee  
    // Call: http://localhost:8080/employee
    $scope.submitTask = function() {    	
    	var _task = {  
                id: "Task" + $scope.taskList.length,  
                description: $scope.taskForm.taskDescription,  
                duration: $scope.taskForm.taskDuration,  
                depends: $scope.taskForm.taskDepends
            };  
            $scope.taskList.push(_task);
            $scope.projectForm.taskList.push(_task);
            _clearFormData();
    };
    
   $scope.calculateProject = function(){
	   
	   var method = "POST";
       var url = "/calculate";
       
       $http({
           method: method,
           url: url,
           data: angular.toJson($scope.projectForm),
           headers: {
               'Content-Type': 'application/json'
           }
       }).then(
    		   function(res) { // success
    	            $scope.taskList = res.data;
    	            $scope.$apply();
    	        }, function(res) { // error
    	            console.log("Error: " + res.status + " : " + res.data);
    	        });
   }
    
   function _clearFormData() {
       $scope.taskForm.taskDescription = "";
       $scope.taskForm.taskDuration = "";
       
   };
   
   
});