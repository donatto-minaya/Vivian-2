$(document).ready(function(){

    if (localStorage.getItem("Success")) {
        toastr.success(localStorage.getItem("Success"));
        localStorage.clear();
    }

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
                    return '<button style="margin-right:5px;" type="button" class="editar" id=n-"' + meta.row + '">Editar</button><button type="button" class="eliminar" id=s-"' + meta.row + '">Eliminar</button>';
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
    const idni = $("#txtDni");
    const inombres = $("#txtNombres");
    const iapellidos = $("#txtApellidos");
    const iusuario = $("#txtUsername");
    const ipassword = $("#txtPassword");
    const irepeatpass = $("#txtRepeatPassword");
    const itel = $("#txtTel");

    XModal.click(function (){
        idni.val("");
        inombres.val("");
        iapellidos.val("");
        iusuario.val("");
        ipassword.val("");
        irepeatpass.val("");
        itel.val("");
        $("label.error").remove();
    });

    $('#tAdminUsuario tbody').on('click', '.editar', function () {
        $("label.error").remove();
        var id = $(this).attr("id").match(/\d+/)[0];
        var data = $('#tAdminUsuario').DataTable().row( id ).data();
        idni.val(data[1]);
        inombres.val(data[2]);
        iapellidos.val(data[3]);
        iusuario.val(data[4]);
        itel.val(data[5]);
        console.log(data[7]);
        switch (data[7])
        {
            case "Activo":  $("#txtEstado").val(1); break;
            default:  $("#txtEstado").val(0);
        }
        openModal();
    });

    $('#tAdminUsuario tbody').on('click', '.eliminar', function () {
        var id = $(this).attr("id").match(/\d+/)[0];
        var data = $('#tAdminUsuario').DataTable().row( id ).data();
        if(!confirm('Desea Eliminar?'))return false;
        $.ajax({
            type: 'DELETE',
            url: '/adminusers',
            data: {
                "id":data[0],
            },success: function (data){
                if (data.estado === 1){
                    localStorage.setItem("Success",data.mensaje);
                    parent.location.href="/adminusers";
                }else{
                    toastr.error(data.mensaje);
                }
            }
        })
    });

    // --------- VALIDACION ----------
    // METODO PARA VALIDAR SOLO LETRAS
    $.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-z\s]+$/i.test(value);
    }, "El campo solo permite el ingreso de letras");
    // METODO PARA VALIDAR VALORES IGUALES
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
            let adminuser = {
                "dni": $("#txtDni").val(),
                "nombresUsuario": $("#txtNombres").val(),
                "apellidosUsuario": $("#txtApellidos").val(),
                "telefono": $("#txtTel").val(),
                "username": $("#txtUsername").val(),
                "password": $("#txtPassword").val(),
            }

            $.ajax({
                type: 'POST',
                url: '/adminusers',
                data: {
                    "dni": $("#txtDni").val(),
                    "nombresUsuario": $("#txtNombres").val(),
                    "apellidosUsuario": $("#txtApellidos").val(),
                    "telefono": $("#txtTel").val(),
                    "username": $("#txtUsername").val(),
                    "password": $("#txtPassword").val(),
                    "estado":$("#txtEstado").val()
                },
                success: function (data){
                    if (data.estado === 1){
                        localStorage.setItem("Success",data.mensaje);
                        parent.location.href = "/adminusers";
                    } else{
                        toastr.warning(data.mensaje)
                    }
                },
                error: function (data){
                    toastr.error(data.responseJSON.mensaje);
                }
            })
        }
    });
    // --------------------------------------------------------------------------------------



});