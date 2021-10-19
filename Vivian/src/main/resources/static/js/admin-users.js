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

    let tabla = $('#tAdminUsuario').DataTable({
        columnDefs: [
            {  targets: 8,
                render: function (data, type, row, meta) {
                    return '<button style="margin-right:5px;" type="button" data-toggle="modal" data-target="#formCat" class="btn btn-warning editar" id=n-"' + meta.row + '">Editar<button type="button" class="btn btn-danger eliminar" id=s-"' + meta.row + '">Eliminar</button>';
                }
            }
        ],
        language: idioma_espanol
    });

    $("#agregarAdminUser").click(function (){
        $("#txtId").hide();
        $("#labelId").hide();
    });

    //// VALIDACION
    // METODO PARA VALIDAR SOLO LETRAS
    $.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    }, "El campo solo permite el ingreso de letras");

    $.validator.addMethod("valueNotEquals", function(value, element, param) {
        return this.optional(element) || value != param;
    }, "Seleccione un valor diferente");

    $("#formAdminUser").validate({
        rules:{
            txtPassword: { required:true, minlength:4, maxlength:18},
            txtRepeatPassword: { required:true, equalTo:"#txtPassword"}
        },
        messages:{

            txtPassword: { required:"El campo es requerido", minlength:'Mínimo 4 caracteres', maxlength:'Máximo 18 caracteres'},
            txtRepeatPassword: { required: "El campo es requerido", equalTo: "Las contraseñas no coinciden"}
        }
    });
    $('#btnGuardarAdminUser').on('click',function(){
        if($('#formAdminUser').valid() == false){
            return false;
        }else{
            $("#formAdminUser").submit();
        }
    });
    /////
});