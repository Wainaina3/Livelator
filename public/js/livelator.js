// This script will get input from browser

function translate() {
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
        headers: {
          'X-WDC-PL-OPT-OUT': $('input:radio[name=serRadio]:radio:checked').val()
        },
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