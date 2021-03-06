function SendAlarm(title, content) {
  Push.create(title, {
    body: content,
    timeout: 4000,
    onClick: function () {
      window.focus();
      this.close();
    }
  });
}

$(document).ready(function() {
var tabla = $("#tablaComentariosBody");
var idSug = parseInt($("#idSugerencia").html());

var socket = new SockJS('/stomp');

var stompClient = Stomp.over(socket);

  stompClient.connect({ }, function(frame) {
  stompClient.subscribe("/topic/comentarios", function(data) {
    var comentario = JSON.parse(data.body);

    if (comentario.suggestion.id === idSug) {
      var htmlString = "\
      <tr>\
        <td align=\"center\">" + comentario.id + "</td>\
        <td>" + comentario.contents + "</td>\
      </tr>\
      ";

      tabla.append(htmlString);

      SendAlarm("Nuevo comentario", comentario.contents);
    }
  });

  stompClient.subscribe("/topic/sugerencias", function(data) {
    var sugerencia = JSON.parse(data.body);

    if (sugerencia.id === idSug) {
        var positivosAntes = parseInt($("#votosPositivos").html());
        var negativosAntes = parseInt($("#votosNegativos").html());
        $("#votosPositivos").html(sugerencia.positiveVotes.toString());
        $("#votosNegativos").html(sugerencia.negativeVotes.toString());
        $("#votosTotales").html("" + (sugerencia.totalVotes));
        $("#pValoracion").html(sugerencia.rating.toString());
        window.myChart.data.datasets[0].data[0] = sugerencia.positiveVotes;
        window.myChart.data.datasets[0].data[1] = sugerencia.negativeVotes;
        window.myChart.update();

        if (sugerencia.positiveVotes > positivosAntes) {
          SendAlarm("Voto positivo", sugerencia.contents);
        }

        if (sugerencia.negativeVotes > negativosAntes) {
          SendAlarm("Voto negativo", sugerencia.contents);
        }
    }
  });
  });
});
