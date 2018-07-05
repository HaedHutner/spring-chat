$(document).ready( function() {

            $(document).on("click", ".method-delete", function() {

                var link = $(this).attr("href");

                $.ajax({
                    url: link,
                    type: "DELETE"
                });
            });

});