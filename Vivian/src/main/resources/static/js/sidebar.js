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

    $('#mantenimientos').click(function (){
        if ($('#fake-combo').hasClass('active-combo')){
            $('#fake-combo').removeClass('active-combo');
        }else{
            $('#fake-combo').addClass('active-combo');
        }

        document.getElementById(this.id).classList.toggle('link-active');
      });
});