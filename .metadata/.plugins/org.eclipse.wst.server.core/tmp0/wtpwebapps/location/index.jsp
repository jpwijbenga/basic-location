<!DOCTYPE html>
<html lang="en" data-ng-app="app">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Location</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/itf.css" rel="stylesheet">
</head>
<body data-ng-controller="AppController">
	<div id="loader"></div>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
						aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Location</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Locations</a></li>
						<li><a href="#">Documentation</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
				<ul class="nav nav-pills nav-stacked">
					<li data-ng-class="menuClass('models')"><a data-ui-sref="models"><span class="glyphicon glyphicon-duplicate"></span> Model Files</a></li>
					<li data-ng-class="menuClass('profiles')"><a data-ui-sref="profiles"><span class="glyphicon glyphicon-list-alt"></span> Profiles</a></li>
					<li data-ng-class="menuClass('parameters')"><a data-ui-sref="parameters"><span class="glyphicon glyphicon-wrench"></span> Parameters</a></li>
					<li data-ng-class="menuClass('weatherstations')"><a data-ui-sref="weatherstations"><span class="glyphicon glyphicon-globe"></span> Weather Stations</a></li>
					<li data-ng-class="menuClass('simulation')"><a data-ui-sref="simulation"><span class="glyphicon glyphicon-triangle-right"></span> Simulation</a></li>
					<li data-ng-class="menuClass('devtools')"><a data-ui-sref="devtools.runs"><span class="glyphicon glyphicon-console"></span> Developer Tools</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-9">
				<flash:messages></flash:messages>
				<div data-ui-view></div>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.fs.dropper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/angular.min.js"></script>
	<script src="assets/js/angular-resource.min.js"></script>
	<script src="assets/js/angular-ui-router.min.js"></script>
	<script src="assets/js/angular-flash.js"></script>
	<script src="app/app.js"></script>
	<script src="app/parameters/parameters.js"></script>
	<script src="app/models/models.js"></script>
	<script src="app/profiles/profiles.js"></script>
	<script src="app/weatherstations/weatherstations.js"></script>
	<script src="app/simulation/simulation.js"></script>
	<script src="app/devtools/runs/runs.js"></script>
	<script src="app/devtools/config/config.js"></script>
	<script src="app/devtools/statistics/statistics.js"></script>
</body>
</html>