$("#comparebutton").click(function() {
		var list1 = $("#list1").val();
		var list2 = $("#list2").val();
		var case_insensitive = $('#case_checkbox').prop('checked');
		if(list1 == '' && list2 == '') {
			return;
		}
		var inputJson = {};
		inputJson.list1 = list1;
		inputJson.list2 = list2;
		inputJson.case_insensitive = case_insensitive;
		var JSONString = JSON.stringify(inputJson);
		$.ajax({
			url: "rest/comparelist",
			type: "POST",
			data : JSONString,
			contentType: 'application/json',
			success: function(data) {
				$("#divcomparetool").hide();
				$('#divcompareresult').show();
				if(case_insensitive) {
					$("#case_display").html("case-insensitive");
				}
				for(key in data) {
					$("#"+key).val(data[key]);
				}
			},
			error: function(jqXHR, exception) {
				$('#errorMsg').html("Validation ERROR : "+jqXHR.responseText);
				$('#outputJson').html("");
			}
		})
	});
	
	$('body').on('click','#formatjson',function(){
		var inputJson = $('#inputjson').val();
		if(inputJson == '')
            return;
		$('#output_col').html('<textarea name="outputjson" id="outputjson" rows="32" cols="60"></textarea>');
		$.ajax({
			url: "rest/jsonformatter",
			type: "POST",
			data : inputJson,
			contentType: 'application/json',
			success: function(data) {
				var notab = parseInt($("#nojsontab").val());
				$('#outputjson').html(JSON.stringify(data, null, notab));
			},
			error: function(jqXHR, exception) {
				$('#outputjson').val(jqXHR.responseText);
			}
		})
	});
	$('body').on('click','#minifyjson',function(){
		var inputJson =  $('#inputjson').val();
		if(inputJson == '')
            return;
		$('#output_col').html('<textarea name="outputjson" id="outputjson" rows="32" cols="60"></textarea>');
		$.ajax({
			url: "rest/jsonformatter",
			type: "POST",
			data : inputJson,
			contentType: 'application/json',
			success: function(data) {
				$('#outputjson').html(JSON.stringify(data));
			},
			error: function(jqXHR, exception) {
				$('#outputjson').val(jqXHR.responseText);
			}
		})
	});
	
	function getTimeZone() {
		$.ajax({
			url: "rest/dateservice/timezone",
			type: "GET",
			success: function(data) {
				LeafProperties.time_zone = data.time_zone;
			}
		})
	}
	
	$('body').on('click','#dateconvert',function(){
		var milliseconds =  $('#milliseconds').val();
		if(milliseconds == '')
            return;
		var inputJson = {};
		inputJson.milliseconds = milliseconds;
		inputJson.local_time_zone = Intl.DateTimeFormat().resolvedOptions().timeZone;
		inputJson.user_time_zone = $('#timezone :selected').text();
		var JSONString = JSON.stringify(inputJson);
		$.ajax({
			url: "rest/dateservice",
			type: "POST",
			data : JSONString,
			contentType: 'application/json',
			success: function(data) {
				$('#dateresult').html(data.user_date);
				$('#user_selected_tz').html(inputJson.user_time_zone);
				$('#localtimezone').html(inputJson.local_time_zone);
				$('#uct_dateresult').html(data.UTC_date);
				$('#local_dateresult').html(data.local_date);
			},
			error: function(jqXHR, exception) {
				$('#dateresult').val(jqXHR.responseText);
			}
		})
	});
	
	$('body').on('click','.encdecbutton',function(){
		var input_enc_dec =  $('#inputencdec').val();
		var action = $(this).val();
		if(input_enc_dec == '')
            return;
		var inputJson = {};
		inputJson.url = input_enc_dec;
		inputJson.charset = $('#toggle_text_charset :selected').text();
		var JSONString = JSON.stringify(inputJson);
		$.ajax({
			url: "rest/encodedecodeservice/"+action,
			type: "POST",
			data : JSONString,
			contentType: 'application/json',
			success: function(data) {
				$('#inputencdec').val(data.EncodedURL);
			},
			error: function(jqXHR, exception) {
				$('#inputencdec').val(jqXHR.responseText);
			}
		})
	});
	
	$('body').on('click','#wordcounter',function(){
		var sentence =  $('#countsentenece').val();
		var action = $(this).val();
		if(sentence == '')
            return;
		var inputJson = {};
		inputJson.input_string = sentence;
		var JSONString = JSON.stringify(inputJson);
		$.ajax({
			url: "rest/counter/sentence",
			type: "POST",
			data : JSONString,
			contentType: 'application/json',
			success: function(data) {
				$('#countresult').html(data.word_count+" word "+data.char_count+" characters");
			},
			error: function(jqXHR, exception) {
				$('#countresult').val(jqXHR.responseText);
			}
		})
	});
