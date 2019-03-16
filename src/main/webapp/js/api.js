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
	
	$('body').on('click','#convert',function(){
		var milliseconds =  $('#milliseconds').val();
		if(milliseconds == '')
            return;
		$.ajax({
			url: "rest/dateservice",
			type: "POST",
			data : milliseconds,
			contentType: 'text/plain',
			success: function(data) {
				$('#dateresult').html(data);
			},
			error: function(jqXHR, exception) {
				$('#dateresult').val(jqXHR.responseText);
			}
		})
	});
