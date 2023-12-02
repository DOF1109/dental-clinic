window.addEventListener('load', function () {
    
    const url = '/odontologo/todos';
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        // Recorremos la colección de odontologos del JSON
        for(odontologo of data){
            // Por cada odontologo armaremos una fila de la tabla
            // Cada fila tendrá un id que nos permitirá actualizar/borrar la fila
            let table = document.getElementById("odontologoTable");
            let odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;

            // Creamos un boton delete para poder eliminar la fila
            // Este invoca a deleteBy que llama a la API para eliminar un odontologo
            let deleteButton = `
                <button id='btn_delete_${odontologo.id}' type="button" 
                    onclick='deleteBy("${odontologo.id}")' class="btn btn-danger btn_delete">
                    &times</button>`;

            // Creamos un boton que muestra el id para poder editar la fila
            // Este invoca a findBy que busca el odontologo que queremos
            // modificar y muestra los datos del mismo en un formulario
            let updateButton = `
                <button id='btn_id_${odontologo.id}' type="button" 
                    onclick='findBy("${odontologo.id}")' class="btn btn-primary btn_id"> 
                    ${odontologo.id}</button>`;

            /*
            let updateButton = '<button' +
                ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                ' type="button" onclick="findBy('+ odontologo.id +')" class="btn btn-primary btn_id">' +
                odontologo.id +
                '</button>';
            */

            // Armamos cada columna de la fila
            odontologoRow.innerHTML = `
                <td>${updateButton}</td>
                <td class='td_matricula'>${odontologo.matricula.toUpperCase()}</td>
                <td class='td_nombre'>${odontologo.nombre.toUpperCase()}</td>
                <td class='td_apellido'>${odontologo.apellido.toUpperCase()}</td>
                <td>${deleteButton}</td>`;

            /*
            odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                '<td>' + deleteButton + '</td>';
            */
        };
    })

})