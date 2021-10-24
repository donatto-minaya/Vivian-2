$(document).ready(function(){

    // VARIABLES DE LOS TODOS LOS INPUTS Y COMBOBOXES DEL FORMULARIO. ADEMAS DE LA "X" PARA CERRAR EL MODAL
    const XModal = $(".btn-close-modal");
    const idni = $("#txtDni");
    const inombres = $("#txtNombres");
    const iapellidos = $("#txtApellidos");
    const iusuario = $("#txtUsername");
    const ipassword = $("#txtPassword");
    const irepeatpass = $("#txtRepeatPassword");
    const itel = $("#txtTel");
    const cboEstado = $("#txtEstado");
    const iid = $("#txtId");

    // AL INCIAR LA APLICACION EL INPUT Y LABEL DEL ID DE USUARIO NO SE MOSTRARAN EN EL MODAL/FORMULARIO
    iid.hide();
    $("#labelId").hide();

    // OBJETO PARA PONER EL DATA TABLE EN ESPANIOL
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

    // CREA EL DATA TABLE CON BOTONES EDITAR Y ELIMINAR A TRAVES DEL ID DE LA TABLA EN EL HTML
    let tabla = $('#tAdminUsuarioCustomer').DataTable({
        columnDefs: [
            {  targets: 8,
                render: function (data, type, row, meta) {
                    return '<button style="margin-right:5px;" type="button" class="editar" id=n-"' + meta.row + '">Editar</button><button type="button" class="eliminar" id=s-"' + meta.row + '">Eliminar</button>';
                }
            }
        ],
        language: idioma_espanol
    });

    // METODO PARA SETEAR EL VALUE "AGREGAR" AL BOTON GUARDAR AL DARLE CLICK EN "Agregar Nuevo" PARA LUEGO UTILIZAR ESTE VALUE EN EL AJAX
    $("#agregarCustomerUser").click(function (){
        $('#btnGuardarCustomerUser').val("agregar");
    });

    // METODO PARA LIMPIAR EL FORMULARIO(inputs y mensajes de errores) AL DARLE CLICK A LA "X" DEL MODAL (cerrar modal)
    XModal.click(function (){
        idni.val("");
        inombres.val("");
        iapellidos.val("");
        iusuario.val("");
        ipassword.val("");
        irepeatpass.val("");
        itel.val("");
        cboEstado.val(1);
        $("label.error").remove();
    });


});
