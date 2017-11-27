(function main() {

  new Vue({
    el: '#app',
    data: {
      savedName: 'Guest',
      name: '',
    },
    methods: {
      onSave() {
        this.savedName = this.name + '!';
        this.name = '';
      },
    },
  });

})();
