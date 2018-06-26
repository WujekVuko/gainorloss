$( document ).ready(function() {
    $( function() {
        var availableTags = [
            "Asbis",
            "Famur",
            "Platige"
        ];
        $( "#name" ).autocomplete({
            source: availableTags
        });
    } );
});