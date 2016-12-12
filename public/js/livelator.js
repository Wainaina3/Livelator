// This script will get input from browser

function translator(){
	alert("button clicked");
	$("outputed").val("Hello you");
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
      	$("#outputed").val(data);
      })
      .fail( function (err){
      	$("#outputed").val(err);
      });
}

function hello(){
	alert("hello");
}