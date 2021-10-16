$(document).ready(function() {
	
    $('#cerrarsesion').click(function(){
        Swal.fire({
            title: '¿Cerrar Sesión?',
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Sí'
        }).then((result) => {
            if (result.isConfirmed) {
                parent.location.href="/logout";
            }
        })
    });

    $('#toggleMenu').click(function (){
      if ($('#fake-aside').hasClass('active')){
          $('#fake-aside').removeClass('active');
      }else{
          $('#fake-aside').addClass('active');
      }
    });
});