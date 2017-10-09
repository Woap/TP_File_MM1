var data3 = data,
	config = {
		data: data3,
		xkey: 'duree',
		ykeys: ['nombre_moyen_client'],
		goals : [5.000000000000002],
		goalLineColors : ['red','red']
		,labels: ['nombre_moyen_client'],
		fillOpacity: 0.8,
		hideHover: 'auto',
		behaveLikeLine: true,
		resize: true,
		pointSize: 3,
		pointFillColors:['#ffffff'],
		pointStrokeColors: ['black'],
		parseTime:false,
		lineColors:['black','black']
	};
config.element = 'log4';
Morris.Line(config);

