var data = [
{ t: '0', nbclient: '5159', sans_att: '0.1383989145183175', avec_att: '0.8616010854816825', debit: '5.159', nb_client: '4.395134716030238', tmps: '1.2182115490383356'},
{ t: '1', nbclient: '5019', sans_att: '0.1589958158995816', avec_att: '0.8410041841004184', debit: '5.019', nb_client: '4.013498704921299', tmps: '1.0928744856856645'},
{ t: '2', nbclient: '5031', sans_att: '0.1524547803617571', avec_att: '0.8475452196382429', debit: '5.031', nb_client: '3.7310673822301728', tmps: '1.0061479560104665'},
{ t: '3', nbclient: '5088', sans_att: '0.15507075471698112', avec_att: '0.8449292452830188', debit: '5.088', nb_client: '4.15846108490566', tmps: '1.1684178004675667'},
{ t: '4', nbclient: '5030', sans_att: '0.15168986083499006', avec_att: '0.84831013916501', debit: '5.03', nb_client: '3.4711729622266403', tmps: '0.9085902827365622'},
{ t: '5', nbclient: '4921', sans_att: '0.18756350335297703', avec_att: '0.812436496647023', debit: '4.921', nb_client: '3.2670697012802274', tmps: '0.8277023183888058'},
{ t: '6', nbclient: '4963', sans_att: '0.1847672778561354', avec_att: '0.8152327221438646', debit: '4.963', nb_client: '3.4416179730002017', tmps: '0.8944087317674402'},
{ t: '7', nbclient: '5051', sans_att: '0.15600871114630765', avec_att: '0.8439912888536923', debit: '5.051', nb_client: '4.3049891110671155', tmps: '1.1995789324794648'},
{ t: '8', nbclient: '5005', sans_att: '0.17202797202797201', avec_att: '0.827972027972028', debit: '5.005', nb_client: '3.794705294705295', tmps: '1.0071603880913358'},
{ t: '9', nbclient: '5103', sans_att: '0.17519106407995297', avec_att: '0.824808935920047', debit: '5.103', nb_client: '3.453654712913972', tmps: '0.8644304734362406'},
],
	config = {
		data: data,
		xkey: 't',
		ykeys: ['nbclient'],
		labels: ['nbclient'],
		fillOpacity: 0.8,
		hideHover: 'auto',
		behaveLikeLine: true,
		resize: true,
		pointFillColors:['#ffffff'],
		pointStrokeColors: ['black'],
		parseTime:false,
		lineColors:['#f4bf42','red','blue']
	};
config.element = 'log1';
Morris.Line(config);
