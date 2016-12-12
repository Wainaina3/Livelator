// This script will get input from browser

function translator(){
	var textContent = $("#inputed").val();

	var callData = {
        source: 'en',
        target: 'es',
        text: textContent
      };

	 var restAPICall = {
        type: 'POST',
        url: '/api/translate',
        data: callData,
        dataType: 'json',
        async: true
      };

      $.ajax(restAPICall)
      .done( function (data) {
      	$("#outputed").val(data.translations);
      	$("#outputed").val(data.translations[0].translation);
      })
      .fail( function (err){
      	$("#outputed").val("Error");
      	$("#outputed").val(err.error_message);
      });
}

function hello(){
	alert("hello");
}