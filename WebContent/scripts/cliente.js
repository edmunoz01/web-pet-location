angular.module('PetLocationABC', []);
angular.module('PetLocationABC').controller(
		'ClienteRESTController',
		function($scope, $http) {
			$scope.longitudMascota = null;
			$scope.latitudMascota = null;
			$scope.idCollar = 125;

			wsUrl = 'ws://' + window.location.host + '/web/PetLocationABC/websocketABC/'+$scope.idCollar;
			// crear el websocket
			var ws = new WebSocket(wsUrl);
			ws.onopen = function(event){
			    console.log('WebSocket connection started');
			    ws.send($scope.idCollar);
			};
			ws.onclose = function(event){
			     console.log("Remote host closed or refused WebSocket connection");
			     console.log(event);
			};
			ws.onmessage = function(event){
				$scope.longitudMascota = event.data;
				$scope.$apply();
			    console.log(event.data);
			};
			
			$scope.registrarse = function() {
				wsUrl = 'ws://' + window.location.host + '/web/PetLocationABC/websocketABC/'+$scope.idCollar;
				// crear el websocket
				ws = new WebSocket(wsUrl);
				var d = new Date();
				var milis = d.getTime();
				ws.onopen = function(event){
				    console.log('WebSocket connection started');
				    ws.send($scope.idCollar);
				    $http.get('/web/PetLocationABC/rest/ClienteInformacionMascota/' + $scope.idCollar).then(
						function(response) {
							console.log(response);
						}, function(response) {
							console.log(response);
							alert('Error! revise la consola');
						});
				};
				ws.onclose = function(event){
				     console.log("Remote host closed or refused WebSocket connection");
				     console.log(event);
				};
				ws.onmessage = function(event){
					$scope.longitudMascota = event.data;
					$scope.$apply();
				    console.log(event.data);
				    var d = new Date();
				    var milisActual = d.getTime();
				    console.log("Latencia = " + (milisActual - milis) + "ms");
				    milis = milisActual;
				};
			};

		});