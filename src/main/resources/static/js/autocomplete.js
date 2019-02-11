$( document ).ready(function() {
    $( function() {
        var availableTags = [
            "Asbis",
            "Famur",
            "Platige",
            "CD Projekt",
            "KGHM",
            "Bitcoin/zloty"

        ];
        $( "#name" ).autocomplete({
            source: availableTags
        });
    } );
});