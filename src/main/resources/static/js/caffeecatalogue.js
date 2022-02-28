//EXTERN: Copied and adapted form Booklist

(function () {
    let Caffeecatalogue = {
        init: function () {
            this.initBeansDetailDeleteForm();
        },
        initBeansDetailDeleteForm: function () {
            document.querySelectorAll('.beans-detail__delete-form').forEach((deleteFormElement) => {
                deleteFormElement.onsubmit = () => {
                    return confirm('Do you really want to delete this bean?');
                };
            });
        }
    };
    document.addEventListener('DOMContentLoaded', () => Caffeecatalogue.init());
})();
