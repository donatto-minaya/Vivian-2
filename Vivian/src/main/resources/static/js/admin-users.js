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

    const XModal = $(".btn-close-modal");
    const ldni = $("#txtDni");
    const lnombres = $("#txtNombres");
    const lapellidos = $("#txtApellidos");
    const lusuario = $("#txtUsername");
    const lpassword = $("#txtPassword");
    const lrepeatpass = $("#txtRepeatPassword");
    const ltel = $("#txtTel");

    XModal.click(function (){
        ldni.val("");
        lnombres.val("");
        lapellidos.val("");
        lusuario.val("");
        lpassword.val("");
        lrepeatpass.val("");
        ltel.val("");
        $("label.error").remove();
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
            dni: { required: true, minlength:8, maxlength:8, digits: true},
            nombresUsuario: { required: true, minlength:2, maxlength:30, lettersonly: true},
            apellidosUsuario: { required: true, minlength:4, maxlength:30, lettersonly: true},
            telefono: { required: true, minlength:7, maxlength:9, digits: true},
            username: { required: true, email: true},
            password: { required:true, minlength:4, maxlength:18},
            txtRepeatPassword: { required:true, equalTo:"#txtPassword"}
        },
        messages:{
            dni: { required:"El campo es requerido", minlength:'Mínimo 8 caracteres', maxlength:'Máximo 8 caracteres', digits: "Solo números"},
            nombresUsuario: { required:"El campo es requerido", minlength:'Mínimo 2 caracteres', maxlength:'Máximo 30 caracteres', lettersonly: "Solo letras"},
            apellidosUsuario: { required:"El campo es requerido", minlength:'Mínimo 4 caracteres', maxlength:'Máximo 30 caracteres', lettersonly: "Solo letras"},
            telefono: { required:"El campo es requerido", minlength:'Mínimo 7 caracteres', maxlength:'Máximo 9 caracteres', digits: "Solo números"},
            username: { required:"El campo es requerido", email: "Formato de email incorrecto"},
            password: { required:"El campo es requerido", minlength:'Mínimo 4 caracteres', maxlength:'Máximo 18 caracteres'},
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