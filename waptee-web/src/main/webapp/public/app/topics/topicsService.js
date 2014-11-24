angular.module('waptee.services', [])
  .factory('topicsService', function($http) {

    var wapteeAPI = {};

    wapteeAPI.getTopics = function() {
      return {
    	  
    	  success : function(f) {
    		  
    		  f([
                 {
               	  id : '',
               	  label : 'Profile',
               	  description : 'first and last name, e-mail, birthday, address'
                 },
                 {
               	  id : '',
               	  label : 'Professional',
               	  description : 'Nonon, nonono nono, nononono...'
                 },
                 {
               	  id : '',
               	  label : 'Body & Health',
               	  description : 'Nonon, nonono nono, nononono...'
                 },
                 {
               	  id : '',
               	  label : 'Custom Topic 1',
               	  description : 'Nonon, nonono nono, nononono...'
                 },
               ]);
    		  
    	  }
      }
    }

    return wapteeAPI;
  });