var data4 = data,
	config = {
		data: data4,
		xkey: 'duree',
		ykeys: ['temps_moyen_sejour'],
		goals : [1.0000000000000002],
		goalLineColors : ['red','red']
		,labels: ['temps_moyen_sejour'],
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
config.element = 'log3';
Morris.Line(config);

