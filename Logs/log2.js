var data2 = data,
	config = {
		data: data2,
		xkey: 'duree',
		ykeys: ['avec_att'],
		goals : [0.8333333333333334],
		goalLineColors : ['red','red']
		,labels: ['avec_att'],
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
config.element = 'log2';
Morris.Line(config);

