$(document).ready(function(){

    let idioma_espanol={
        "sProcessing":     "Procesando...",
        "sLengthMenu":     "Mostrar _MENU_ registros",
        "sZeroRecords":    "No se encontraron resultados",
        "sEmptyTable":     "Ningún dato disponible en esta tabla",
        "sInfo":           "Mostrando registros del _START_ al _END_ de _TOTAL_ registros",
        "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
        "sInfoFiltered":   "(filtrado de un total de MAX registros)",
        "sSearch":         "Buscar:",
        "sInfoThousands":  ",",
        "sLoadingRecords": "Cargando...",
        "oPaginate": {
            "sFirst":    "Primero",
            "sLast":     "Último",
            "sNext":     "Siguiente",
            "sPrevious": "Anterior"
        },
        "oAria": {
            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
        },
        "buttons": {
            "copy": "Copiar",
            "colvis": "Visibilidad"
        }
    }

    let tabla = $('#tUsuario').DataTable({
        columnDefs: [
            {  targets: 3,
                render: function (data, type, row, meta) {
                    return '<button style="margin-right:5px;" type="button" data-toggle="modal" data-target="#formCat" class="btn btn-warning editar" id=n-"' + meta.row + '">Editar<button type="button" class="btn btn-danger eliminar" id=s-"' + meta.row + '">Eliminar</button>';
                }
            }
        ],
        language: idioma_espanol
    });



});