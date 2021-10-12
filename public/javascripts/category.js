$(document).ready(function(){
    var $table= $(".tableCRUD");
    var dataURl=$table.data('list');
    $.get(dataURl,function(categories){
        $.each(categories,function(index,category){
            var B = (category.parentCategory != undefined) ? category.parentCategory.value :"None";
            var row = "<tr><td>"+category.id.value+"</td><td>" + category.name + "</td><td>" + B + "</td></tr>";
            $('tbody').append(row)
        });
    });
});

$(document).ready(function(){
    $( "form" ).on( "submit", function(e) {
        e.preventDefault();
        var url = $(this).attr("action"),
            method = $(this).attr("method"),
            data = $(this).serialize();

        var token =  $('input[name="csrfToken"]').attr('value')
                    $.ajaxSetup({
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader('Csrf-Token', token);
                        }
                    });

        $.ajax({
            url: url,
            type: method,
            data:data,
            success: function(response){
                console.log(response)
            }
        });


    });
});


