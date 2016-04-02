angular.module('PetLocationABC', []);
angular.module('PetLocationABC').controller(
		'PruebaRESTController',
		function($scope, $http) {
			$scope.latitud = '4.687267';
			$scope.longitud = '-74.063988';
			$scope.idCollar = 1124;
			$scope.frecRespiratoria = 70;
			$scope.frecCardiaca = 120;

			$scope.longitudRespuesta = null;
			$scope.latitudRespuesta = null;

			$scope.servicioPrueba = function() {
				var req = {
					method : 'GET',
					url : '/web/PetLocationABC/rest/InformacionMascota/prueba'
				};
				$http(req).then(function(response) {
					console.log(response);
				}, function(response) {
					console.log(response);
					alert('Error! revise la consola');
				});
			};

			$scope.enviarInformacion = function() {
				var req = {
					'latitud' : $scope.latitud,
					'longitud' : $scope.longitud,
					'idCollar' : $scope.idCollar,
					'frecuenciaRespiratoria' : $scope.frecRespiratoria,
					'frecuenciaCardiaca' : $scope.frecCardiaca
				};
				$scope.longitudRespuesta = null;
				$scope.latitudRespuesta = null;
				$http.post('/web/PetLocationABC/rest/InformacionMascota', req).then(
						function(response) {
							console.log(response);
							$scope.longitudRespuesta = response.data.longitud;
							$scope.latitudRespuesta = response.data.latitud;
						}, function(response) {
							console.log(response);
							alert('Error! revise la consola');
						});
			};
		});