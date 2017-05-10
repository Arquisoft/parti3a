$(document).ready(function() {
var tabla = $("#tablaSugerenciasBody");

var socket = new SockJS('/stomp');

var stompClient = Stomp.over(socket);

  stompClient.connect({ }, function(frame) {
  stompClient.subscribe("/topic/sugerencias", function(data) {
    var sugerencia = JSON.parse(data.body);
    var encontrado = false;

    $(".titSugerencia").each(function(i) {
      if (this.innerHTML === sugerencia.contents) {
        $(this).next().next().html("" + (sugerencia.positiveVotes + sugerencia.negativeVotes));
        encontrado = true;
        return false;
      }
    });

    if (!encontrado) {
      var htmlstring = "\
      <tr>\
        <td>" + sugerencia.id + "</td>\
        <td class=\"titSugerencia\">" + sugerencia.contents + "</td>\
        <td>" + moment(new Date(sugerencia.creationDate)).format("YYYY-MM-DD") + "</td>\
        <td class=\"votos_totales\" align=\"center\">" + sugerencia.totalVotes + "</td>\
        <td align=\"center\">" + sugerencia.numComments + "</td>\
        <td><a href=\"sugerencias/" + sugerencia.id + "\">Detalles</a></td>\
      </tr>\
      ";
       tabla.append(htmlstring);
    }
  });
  });
});
