var noPhoto = 'data:image/png;base64,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';
var j = $.noConflict();
//var apiBaseUrl = 'http://localhost:8080/tennisfolks';
//var baseUrl = 'http://localhost:8080/tennisfolks/api/';
var apiBaseUrl = 'http://tennisfolks-tennisfolks.rhcloud.com';
var baseUrl = 'http://tennisfolks-tennisfolks.rhcloud.com/api/';
var app = angular.module('app', ['ngRoute',  'ngResource', 'ngCookies', 'ui.bootstrap']);

// Enable html push state
app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider
	.when('/', {
        redirectTo : '/en'
    })
    .when('/:lang', {
        templateUrl : 'views/home.html',
        controller : 'homeController'
    })
    .when('/:lang/people', {
        templateUrl : 'views/users/list.html',
        controller : 'peopleListController'
    })
    .when('/:lang/people/:id', {
        templateUrl : 'views/users/details.html',
        controller : 'peopleDetailsController'
    })
    .when('/:lang/people/:id/edit', {
        templateUrl : 'views/users/edit.html',
        controller : 'peopleEditController'
    })
    .when('/:lang/people/:id/followers', {
        templateUrl : 'views/connections/followers/list.html',
        controller : 'peopleFollowersController'
    })
    .when('/:lang/people/:id/following', {
        templateUrl : 'views/connections/following/list.html',
        controller : 'peopleFollowingController'
    })
    .when('/:lang/people/:id/blog/:entryId', {
    	templateUrl : 'views/users/blog/details.html', 
    	controller : 'peopleBlogEntryController'
    })
    .when('/:lang/leagues', {
    	templateUrl : 'views/leagues/list.html', 
    	controller : 'leagueListController', 
    	activetab : 'all'
    })
    .when('/:lang/leagues/create', {
    	templateUrl : 'views/leagues/create.html', 
    	controller : 'createLeagueController'
    })
    .when('/:lang/leagues/membership/:playerId', {
    	templateUrl : 'views/leagues/list.html',
    	controller : 'membershipLeagueListController', 
    	activetab : 'membership'
    })
    .when('/:lang/leagues/management/:playerId', {
    	templateUrl : 'views/leagues/list.html',
    	controller : 'managementLeagueListController', 
    	activetab : 'management'
    })
    .when('/:lang/leagues/:leagueId', {
    	templateUrl : 'views/leagues/details.html', 
    	controller : 'leagueDetailsController'
    })
    .when('/:lang/leagues/:leagueId/edit', {
    	templateUrl : 'views/leagues/edit.html', 
    	controller : 'editLeagueController'
    })
    .when('/:lang/leagues/:leagueId/tournaments', {
    	templateUrl : 'views/leagues/tournaments/list.html', 
    	controller : 'leagueTournamentListController'
    })
    .when('/:lang/leagues/:leagueId/tournaments/:tournamentId', {
    	templateUrl : 'views/leagues/tournaments/details.html', 
    	controller : 'leagueTournamentDetailsController'
    })
    .when('/:lang/leagues/:leagueId/tournaments/:tournamentId/participants', {
    	templateUrl : 'views/leagues/tournaments/participants/list.html', 
    	controller : 'leagueTournamentParticipantsListController'
    })
    .when('/:lang/leagues/:leagueId/tournaments/:tournamentId/matches', {
    	templateUrl : 'views/leagues/tournaments/matches/list.html', 
    	controller : 'leagueTournamentMatchesListController'
    })
    .when('/:lang/leagues/:leagueId/tournaments/:tournamentId/matches/:matchId', {
    	templateUrl : 'views/leagues/tournaments/matches/details.html', 
    	controller : 'leagueTournamentMatchDetailsController'
    })
    .when('/:lang/leagues/:leagueId/manage', {
    	redirectTo : '/:lang/leagues/:leagueId/manage/requests'
    })
    .when('/:lang/leagues/:leagueId/manage/requests', {
    	templateUrl : 'views/leagues/manage/requests/list.html', 
    	controller : 'manageLeagueRequestsController'
    })
    .when('/:lang/leagues/:leagueId/manage/members', {
    	templateUrl : 'views/leagues/manage/members/list.html', 
    	controller : 'manageLeagueMembersController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments', {
    	templateUrl : 'views/leagues/manage/tournaments/list.html', 
    	controller : 'manageLeagueTournamentsController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/create', {
    	templateUrl : 'views/leagues/manage/tournaments/create.html', 
    	controller : 'manageLeagueCreateTournamentController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/:tournamentId', {
    	templateUrl : 'views/leagues/manage/tournaments/edit.html', 
    	controller : 'manageLeagueEditTournamentController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/:tournamentId/participants', {
    	templateUrl : 'views/leagues/manage/tournaments/participants/list.html', 
    	controller : 'manageLeagueTournamentParticipantsController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/:tournamentId/matches', {
    	templateUrl : 'views/leagues/manage/tournaments/matches/list.html', 
    	controller : 'manageLeagueTournamentMatchesController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/:tournamentId/matches/create', {
    	templateUrl : 'views/leagues/manage/tournaments/matches/create.html', 
    	controller : 'createLeagueTournamentMatchController'
    })
    .when('/:lang/leagues/:leagueId/manage/tournaments/:tournamentId/matches/:matchId', {
    	templateUrl : 'views/leagues/manage/tournaments/matches/edit.html', 
    	controller : 'editLeagueTournamentMatchController'
    })
    .when('/:lang/leagues/:leagueId/blog/:entryId', {
    	templateUrl : 'views/leagues/blog/details.html', 
    	controller : 'leagueBlogEntryController'
    })
    .when('/:lang/messages', {
    	templateUrl : 'views/messages/list.html',
    	controller : 'messagesListController'
    })
    .when('/:lang/messages/:dialogueId', {
    	templateUrl : 'views/messages/details.html',
    	controller : 'messagesDetailsController'
    })
    .when('/:lang/open', {
    	templateUrl : 'views/challenges/open/list.html',
    	controller : 'openChallengeListController', 
    	activetab : 'open'
    })
    .when('/:lang/open/new', {
    	templateUrl : 'views/challenges/open/create.html',
    	controller : 'newOpenChallengeController'
    })
    .when('/:lang/open/player/:playerId', {
    	templateUrl : 'views/challenges/open/list.html',
    	controller : 'playerOpenChallengeController', 
    	activetab : 'open'
    })
    .when('/:lang/open/:challengeId', {
    	templateUrl : 'views/challenges/open/details.html',
    	controller : 'openChallengeDetailsController'
    })
    .when('/:lang/open/:challengeId/edit', {
    	templateUrl : 'views/challenges/open/details.html',
    	controller : 'openChallengeDetailsController'
    })
    .when('/:lang/challenges', {
    	templateUrl : 'views/challenges/regular/list.html',
    	controller : 'challengeMatchListController', 
    	activetab : 'matches'
    })
    .when('/:lang/challenges/new/:playerId', {
    	templateUrl : 'views/challenges/regular/create.html',
    	controller : 'newChallengeMatchController'
    })
    .when('/:lang/challenges/player/:playerId', {
    	templateUrl : 'views/challenges/regular/list.html',
    	controller : 'playerChallengeMatchController', 
    	activetab : 'matches'
    })
    .when('/:lang/challenges/:challengeId', {
    	templateUrl : 'views/challenges/regular/details.html',
    	controller : 'challengeMatchDetailsController'
    })
    .when('/:lang/challenges/:challengeId/edit', {
    	templateUrl : 'views/challenges/regular/edit.html',
    	controller : 'challengeMatchDetailsController'
    })
    .when('/:lang/tournaments', {
		templateUrl : 'views/tournaments/list.html',
		controller : 'tournamentsController'
	})
	.when('/:lang/tournaments/matches', {
		templateUrl : 'views/tournaments/matches/list.html',
		controller : 'tournamentMatchesController'
	})
	.when('/:lang/signout', {
		templateUrl : 'views/signup.html',
		controller : 'signoutController'
	})
	.when('/:lang/password', {
		templateUrl : 'views/password.html'
	})
	.when('/:lang/signup', {
		templateUrl : 'views/signup.html',
		//controller : 'signupInitController'
	});
    //.otherwise({
    //	templateUrl : 'views/users.html',
    //	controller : 'usersController.getAll'
    //});
	$locationProvider.html5Mode({ enabled: true, requireBase: true });	
}]);

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('authSessionService', ['$cookies', function($cookies) {
	this.getId = function() {
		if ($cookies.getObject('authSession') != null) {
			var session = $cookies.getObject('authSession');
			return session.id;
		} else { return null; }
	}
	this.getToken = function() {
		if ($cookies.getObject('authSession') != null) {
			var session = $cookies.getObject('authSession');
			return session.token;
		} else { return null; }
	}
	this.getSession = function() {
		return ($cookies.getObject('authSession') != null) ? $cookies.getObject('authSession') : { id : null, token : null };
	};
	this.setSession = function (id, token) {
		$cookies.putObject('authSession', { id : id, token : token });
	}
}]);
app.service('helperService', ['$http', function($http) {
	this.newMessages = function(id, token) {
		return $http.get(baseUrl + 'helper/' + id + '/messages?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.newChallenges = function(id, token) {
		return $http.get(baseUrl + 'helper/' + id + '/challenges?token=' + token).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('leagueService', ['$http', function($http) {
	this.getLeagues = function(page) {
		return $http.get(baseUrl + 'leagues?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.search = function(queryString, page) {
		return $http.get(baseUrl + 'leagues/search?' + queryString + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.createLeague = function(league, token) {
		return $http.post(baseUrl + 'leagues?token=' + token, league).then(function(response) {
			return response.data;
		});
	};
	this.getLeagueById = function(id) {
		return $http.get(baseUrl + 'leagues/' + id).then(function(response) {
			return response.data;
		});
	};
	this.searchMembersById = function(leagueId, id, page) {
		return $http.get(baseUrl + 'leagues/' + leagueId + '/members/accepted/search?userId=' + id + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getAcceptedMembers = function(id, page) {
		return $http.get(baseUrl + 'leagues/' + id + '/members/accepted?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getRejectedMembers = function(id, page) {
		return $http.get(baseUrl + 'leagues/' + id + '/members/rejected?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getMembersRequests = function(id, page) {
		return $http.get(baseUrl + 'leagues/' + id + '/members/requests?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getDeletedMembers = function(id, page) {
		return $http.get(baseUrl + 'leagues/' + id + '/members/deleted?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.isAccepted = function(leagueId, userId) {
		return $http.get(baseUrl + 'leagues/' + leagueId + '/membership/accepted?userId=' + userId).then(function(response) {
			return response.data;
		});
	};
	this.isRequestor = function(leagueId, userId) {
		return $http.get(baseUrl + 'leagues/' + leagueId + '/membership/requestor?userId=' + userId).then(function(response) {
			return response.data;
		});
	};
	this.isRejected = function(leagueId, userId) {
		return $http.get(baseUrl + 'leagues/' + leagueId + '/membership/rejected?userId=' + userId).then(function(response) {
			return response.data;
		});
	};
	this.isDeleted = function(leagueId, userId) {
		return $http.get(baseUrl + 'leagues/' + leagueId + '/membership/deleted?userId=' + userId).then(function(response) {
			return response.data;
		});
	};
	this.join = function(leagueId, token) {
		return $http.post(baseUrl + 'leagues/' + leagueId + '/join?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.acceptRequest = function(leagueId, userId, token) {
		return $http.post(baseUrl + 'leagues/' + leagueId + '/management/accept?token=' + token, userId).then(function(response) {
			return response.data;
		});
	};
	this.rejectRequest = function(leagueId, userId, token) {
		return $http.post(baseUrl + 'leagues/' + leagueId + '/management/reject?token=' + token, userId).then(function(response) {
			return response.data;
		});
	};
	this.deleteMember = function(leagueId, userId, token) {
		return $http.post(baseUrl + 'leagues/' + leagueId + '/management/delete?token=' + token, userId).then(function(response) {
			return response.data;
		});
	};
	this.restoreMember = function(leagueId, userId, token) {
		return $http.post(baseUrl + 'leagues/' + leagueId + '/management/restore?token=' + token, userId).then(function(response) {
			return response.data;
		});
	};
	this.updateLeague = function(leagueId, league, token) {
		return $http.put(baseUrl + 'leagues/' + leagueId + '?token=' + token, league).then(function(response) {
			return response.data;
		});
	};
	this.setLogo = function(id, token, imageId) {
    	return $http.post(baseUrl + 'leagues/' + id + '/logo?token=' + token, imageId).then(function(response) {
    		return response.data;
    	});
    };
    this.setCover = function(id, token, imageId) {
    	return $http.post(baseUrl + 'leagues/' + id + '/cover?token=' + token, imageId).then(function(response) {
    		return response.data;
    	});
    };
    this.getMemberLeagues = function(id, page) {
    	return $http.get(baseUrl + 'leagues/member/' + id + '?page=' + page).then(function(response) {
    		return response.data;
    	});
    };
    this.getManagedLeagues = function(id, page) {
    	return $http.get(baseUrl + 'leagues/managed/by/' + id + '?page=' + page).then(function(response) {
    		return response.data;
    	});
    };
    this.getBlogEntries = function(id, page) {
		return $http.get(baseUrl + 'leagues/' + id + '/blog?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getBlogEntry = function(id, token) {
		return $http.get(baseUrl + 'leagues/blog/' + id + '?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.createBlogEntry = function(id, token, entry) {
		return $http.post(baseUrl + 'leagues/' + id + '/blog?token=' + token, entry).then(function(response) {
			return response.data;
		});
	};
	this.getBlogEntryComments = function(id, page) {
		return $http.get(baseUrl + 'leagues/blog/' + id + '/comments?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.createBlogEntryComment = function(id, token, comment) {
		return $http.post(baseUrl + 'leagues/blog/' + id + '/comments?token=' + token, comment).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('tournamentService', ['$http', function($http) {
	this.getAll = function(queryString, page) {
		return $http.get(baseUrl + 'tournaments?page=' + page + queryString).then(function(response) {
			return response.data;
		});
	};
	this.getById = function(id) {
		return $http.get(baseUrl + 'tournaments/' + id).then(function(response) {
			return response.data;
		});
	};
	this.create = function(tournament, leagueId, token) {
		return $http.post(baseUrl + 'tournaments?league=' + leagueId + '&token=' + token, tournament).then(function(response) {
			return response.data;
		});
	};
	this.update = function(id, tournament, token) {
		return $http.put(baseUrl + 'tournaments/' + id + '?token=' + token, tournament).then(function(response) {
			return response.data;
		});
	};
	this.deleteTournament = function(id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/delete?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.restoreTournament = function(id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/restore?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.getParticipants = function(id, page) {
		return $http.get(baseUrl + 'tournaments/' + id + '/participants?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.createParticipant = function(userId, id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/participants?token=' + token, userId).then(function(response) {
			return response.data;
		});
	};
	this.confirmParticipant = function(participantId, id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/participants/confirm?token=' + token, participantId).then(function(response) {
			return response.data;
		});
	};
	this.unconfirmParticipant = function(participantId, id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/participants/unconfirm?token=' + token, participantId).then(function(response) {
			return response.data;
		});
	};
	this.deleteParticipant = function(participantId, id, token) {
		return $http.post(baseUrl + 'tournaments/' + id + '/participants/delete?token=' + token, participantId).then(function(response) {
			return response;
		});
	};
	this.isParticipant = function(userId, id) {
		return $http.get(baseUrl + 'tournaments/' + id + '/participants/' + userId + '/status').then(function(response) {
			return response.data;
		});
	};
	this.isConfirmedParticipant = function(userId, id) {
		return $http.get(baseUrl + 'tournaments/' + id + '/participants/' + userId + '/confirmed').then(function(response) {
			return response.data;
		});
	};
	this.getMatches = function(tournamentId, page, queryString) {
		return $http.get(baseUrl + 'tournaments/' + tournamentId + '/matches?page=' + page + queryString).then(function(response) {
			return response.data;
		});
	};
	this.getMatch = function(id) {
		return $http.get(baseUrl + 'tournaments/matches/' + id).then(function(response) {
			return response.data;
		});
	};
	this.finishMatch = function(id, token) {
		return $http.post(baseUrl + 'tournaments/matches/' + id + '/finish?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.approveMatch = function(id, token) {
		return $http.post(baseUrl + 'tournaments/matches/' + id + '/approve?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.deleteMatch = function(id, token) {
		return $http.post(baseUrl + 'tournaments/matches/' + id + '/delete?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.restoreMatch = function(id, token) {
		return $http.post(baseUrl + 'tournaments/matches/' + id + '/restore?token=' + token, {}).then(function(response) {
			return response.data;
		});
	};
	this.updateMatch = function(id, token, match) {
		return $http.put(baseUrl + 'tournaments/matches/' + id + '?token=' + token, match).then(function(response) {
			return response.data;
		});
	};
	this.createMatch = function(id, token, match) {
		return $http.post(baseUrl + 'tournaments/' + id + '/matches?token=' + token, match).then(function(response) {
			return response.data;
		});
	};
	this.searchParticipantsById = function(tournamentId, userId, page) {
		return $http.get(baseUrl + 'tournaments/' + tournamentId + '/participants/search?page=' + page + '&userId=' + userId).then(function(response) {
			return response.data;
		});
	};
	this.getUserTournaments = function(id, page, queryString) {
		return $http.get(baseUrl + 'tournaments/user/' + id + '?page=' + page + queryString).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('languageService', ['$http', function($http) {
	this.getAll = function() {
		return $http.get(baseUrl + 'languages').then(function(response) {
			return response.data;
		});
	};
}]);
app.service('locationService', ['$http', function($http) {
	this.getCountries = function(lang) {
		return $http.get(baseUrl + 'locations/countries?lang=' + lang).then(function(response) {
			return response.data;
		});
	};
	this.getCountryByCode = function(code, lang) {
		return $http.get(baseUrl + 'locations/countries/' + code + '?lang=' + lang).then(function(response) {
			return response.data;
		});
	};
	this.getDivisions = function(code) {
		return $http.get(baseUrl + 'locations/countries/' + code + '/divisions').then(function(response) {
			return response.data;
		});
	};
	this.getCities = function(city, state, country) {
		return $http.get(baseUrl + 'locations/countries/' + country + '/cities?division=' + state + '&query=' + city).then(function(response) {
			return response.data;
		});
	}; 
}]);
app.service('imageService', ['$http', function($http) {
	this.render = function(image) {
    	return $http.get(baseUrl + 'images/' + imageId).then(function(response) {
    		return response.data;
    	});
    };
    this.upload = function(file, token) {
        var formData = new FormData();
        formData.append('file', file);
        return $http.post(baseUrl + 'images?token=' + token, formData, {
            transformRequest : angular.identity,
            headers : {'Content-Type': undefined}
        }).then(function(response){
        	return response.data;
        });
    }; 
    this.discard = function(token, imageId) {
    	$http({method : 'DELETE', url : baseUrl + 'images/' + imageId + '?token=' + token}).then(function(response) { });
    };
}]);
app.service('messageService', ['$http', function($http) {
	this.send = function(token, senderId, recipientId, body) {
		message = { senderId : senderId, recipientId : recipientId, body : body };
		return $http.post(baseUrl + 'dialogues?token=' + token, message).then(function(response) {
			return response.data;
		});
	};
	this.getDialogues = function(token, page) {
		return $http.get(baseUrl + 'dialogues?token=' + token + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getMessages = function(dialogueId, token, page) {
		return $http.get(baseUrl + 'dialogues/' + dialogueId + '?token=' + token + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('challengeMatchService', ['$http', function($http) {
	this.create = function(challenge, token) {
		return $http.post(baseUrl + 'challenges/newmatch?token=' + token, challenge).then(function(response) {
			return response.data;
		});
	};
	this.getById = function(id) {
		return $http.get(baseUrl + 'challenges/' + id).then(function(response) {
			return response.data;
		});
	};
	this.accept = function(id, token) {
		return $http.post(baseUrl + 'challenges/' + id + '/accept?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.reject = function(id, token) {
		return $http.post(baseUrl + 'challenges/' + id + '/reject?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.finish = function(id, token) {
		return $http.post(baseUrl + 'challenges/' + id + '/finish?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.update = function(id, token, challenge) {
		return $http.put(baseUrl + 'challenges/' + id + '?token=' + token, challenge).then(function(response) {
			return response.data;
		});
	};
	this.getAll = function(queryString, page) {
		return $http.get(baseUrl + 'challenges?page=' + page + queryString).then(function(response) {
			return response.data;
		});
	};
	this.getChallengesByPlayer = function(playerId, page) {
		return $http.get(baseUrl + 'challenges?firstPlayer=' + playerId + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('openChallengeService', ['$http', function($http) {
	this.create = function(challenge, token) {
		return $http.post(baseUrl + 'challenges/open?token=' + token, challenge).then(function(response) {
			return response.data;
		});
	};
	this.getById = function(id) {
		return $http.get(baseUrl + 'challenges/open/' + id).then(function(response) {
			return response.data;
		});
	};
	this.accept = function(id, token) {
		return $http.post(baseUrl + 'challenges/open/' + id + '/accept?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.update = function(id, token, challenge) {
		return $http.put(baseUrl + 'challenges/open/' + id + '?token=' + token, challenge).then(function(response) {
			return response.data;
		});
	};
	this.getAll = function(queryString, page) {
		return $http.get(baseUrl + 'challenges/open?page=' + page + queryString).then(function(response) {
			return response.data;
		});
	};
	this.getChallengesByPlayer = function(playerId, page) {
		return $http.get(baseUrl + 'challenges/open?firstPlayer=' + playerId + '&page=' + page).then(function(response) {
			return response.data;
		});
	};
}]);
app.service('userService', ['$http', function($http) {
	this.setPhoto = function(id, token, imageId) {
    	return $http.post(baseUrl + 'users/' + id + '/photo?token=' + token, imageId).then(function(response) {
    		return response.data;
    	});
    };
    this.follow = function(id, token) {
    	return $http.post(baseUrl + 'users/follow?token=' + token, id).then(function(response) {
    		return response.data;
    	});
    };
    this.unfollow = function(id, token) {
    	return $http.post(baseUrl + 'users/unfollow?token=' + token, id).then(function(response) {
    		return response.data;
    	});
    };
    this.checkIfFollowing = function(id, token) {
    	return $http.get(baseUrl + 'users/' + id + '/check/following?token=' + token).then(function(response) {
    		return response.data;
    	});
    };
    this.getFollowers = function(id, page) {
    	return $http.get(baseUrl + 'users/' + id + '/followers?page=' + page).then(function(response) {
    		return response.data;
    	});
    };
    this.getFollowing = function(id, page) {
    	return $http.get(baseUrl + 'users/' + id + '/following?page=' + page).then(function(response) {
    		return response.data;
    	});
    };
	this.getFeatured = function() {
		return $http.get(baseUrl + 'users/featured').then(function(response) {
			return response.data;
		});
	};
	this.getAll = function(page) {
		return $http.get(baseUrl + 'users?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getById = function(id) {
		return $http.get(baseUrl + 'users/' + id).then(function(response) {
			return response.data;
		});
	};
	this.search = function(queryString, page) {
		return $http.get(baseUrl + 'users?page=' + page + '&' + queryString).then(function(response) {
			return response.data;
		});
	};
	this.update = function(id, token, user) {
		return $http.put(baseUrl + 'users/' + id + '?token=' + token, user).then(function(response) {
			return response.data;
		});
	};
	this.getBlogEntries = function(id, page) {
		return $http.get(baseUrl + 'users/' + id + '/blog?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.getBlogEntry = function(id, token) {
		return $http.get(baseUrl + 'users/blog/' + id + '?token=' + token).then(function(response) {
			return response.data;
		});
	};
	this.createBlogEntry = function(id, token, entry) {
		return $http.post(baseUrl + 'users/' + id + '/blog?token=' + token, entry).then(function(response) {
			return response.data;
		});
	};
	this.getBlogEntryComments = function(id, page) {
		return $http.get(baseUrl + 'users/blog/' + id + '/comments?page=' + page).then(function(response) {
			return response.data;
		});
	};
	this.createBlogEntryComment = function(id, token, comment) {
		return $http.post(baseUrl + 'users/blog/' + id + '/comments?token=' + token, comment).then(function(response) {
			return response.data;
		});
	};
}]);
app.controller('homeController', ['$scope', 'userService', 'openChallengeService', function($scope, userService, openChallengeService) {
	userService.getFeatured().then(function(data) {
		$scope.users = data;
	}).catch(function() {
		$scope.users = [];
	});
	openChallengeService.getAll('', 1).then(function(data) {
		$scope.challenges = data.results;
	}).catch(function() {
		$scope.challenges = [];
	});
	$scope.accept = function(challengeId) {
		openChallengeService.accept(challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { $route.reload(); });
	};
	$scope.isSamePerson = function(userId, personId) {
		return (userId == personId) ? true : false;
	};
}]);
app.controller('peopleListController', ['$scope', 'userService', function($scope, userService) {
	$scope.nearby = false;
	userService.search('&nearby=' + $scope.nearby, 1).then(function(data) {
		$scope.users = data.results;
		$scope.currentPage = data.currentPage;
		$scope.nextPage = data.nextPage;
		$scope.nearby = data.nearby;
	}).catch(function() {
		$scope.users = [];
	});
	$scope.isSamePerson = function(userId, personId) {
		return (userId == personId) ? true : false;
	};
	$scope.showMore = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.search('&nearby=' + $scope.nearby, nextPage).then(function(data) {
				for (var i = 0; i < data.results.length; i++) {
					$scope.users.push(data.results[i]);
				}
				$scope.currentPage = data.currentPage;
				$scope.nextPage = data.nextPage;
				$scope.nearby = data.nearby;
			});
		}
	};
	$scope.showNearbyOnly = function(nearby) {
		$scope.nearby = nearby;
		userService.search('&nearby=' + $scope.nearby, 1).then(function(data) {
			$scope.users = data.results;
			$scope.currentPage = data.currentPage;
			$scope.nextPage = data.nextPage;
			$scope.nearby = data.nearby;
		}).catch(function() {
			$scope.users = [];
		});
	};
}]);
app.controller('peopleFollowersController', ['$http', '$scope', '$routeParams', 'userService', 'authSessionService', function($http, $scope, $routeParams, userService, authSessionService) {
	userService.getFollowers($routeParams.id, 1).then(function(data) {
		$scope.users = data.results;
		$scope.currentPage = data.currentPage;
		$scope.nextPage = data.nextPage;
	});
	$scope.showMore = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.getFollowers($routeParams.id, nextPage).then(function(data) {
				for (var i = 0; i < data.results.length; i++) {
					$scope.users.push(data.results[i]);
				}
				$scope.currentPage = data.currentPage;
				$scope.nextPage = data.nextPage;
			});
		}
	};
	$scope.isSamePerson = function(id) {
		return (id == authSessionService.getId()) ? true : false;
	};
	$scope.follow = function(id) {
		userService.follow(id, authSessionService.getToken()).then(function(data) { });
	};
	$scope.unfollow = function(id) {
		userService.unfollow(id, authSessionService.getToken()).then(function(data) { });
	};
}]);
app.controller('peopleFollowingController', ['$http', '$scope', '$routeParams', 'userService', 'authSessionService', function($http, $scope, $routeParams, userService, authSessionService) {
	userService.getFollowing($routeParams.id, 1).then(function(data) {
		$scope.users = data.results;
		$scope.currentPage = data.currentPage;
		$scope.nextPage = data.nextPage;
	});
	$scope.showMore = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.getFollowing($routeParams.id, nextPage).then(function(data) {
				for (var i = 0; i < data.results.length; i++) {
					$scope.users.push(data.results[i]);
				}
				$scope.currentPage = data.currentPage;
				$scope.nextPage = data.nextPage;
			});
		}
	};
	$scope.isSamePerson = function(id) {
		return (id == authSessionService.getId()) ? true : false;
	};
	$scope.follow = function(id) {
		userService.follow(id, authSessionService.getToken()).then(function(data) { });
	};
	$scope.unfollow = function(id) {
		userService.unfollow(id, authSessionService.getToken()).then(function(data) { });
	};
}]);
app.controller('peopleDetailsController', ['$http', '$q', '$scope', '$route', '$routeParams', '$templateCache', 'userService', 'messageService', 'locationService', 'imageService', 'authSessionService', function($http, $q, $scope, $route, $routeParams, $templateCache, userService, messageService, locationService, imageService, authSessionService) {
	userService.getById($routeParams.id).then(function(data) {
		$scope.user = data;
		locationService.getCountryByCode(data.country, $routeParams.lang).then(function(result) {
			if (result !== null) $scope.user.country = result.name;
		});
	}).catch(function() {
		$scope.user = {};
	});
	userService.checkIfFollowing($routeParams.id, authSessionService.getToken()).then(function(data) {
		$scope.isFollowing = data;
	});
	$scope.follow = function() {
		userService.follow($routeParams.id, authSessionService.getToken()).then(function(data) { 
			if ((data != null) && (data.id > 0)) {
				$scope.isFollowing = true;
			} else { $scope.isFollowing = false; }
		});
	};
	$scope.unfollow = function() {
		userService.unfollow($routeParams.id, authSessionService.getToken()).then(function(data) {
			if ((data != null) && (data > 0)) { 
				$scope.isFollowing = false;
			} else { $scope.isFollowing = true; }
		});
	};
	$scope.entries = [];
	userService.getBlogEntries($routeParams.id, 1).then(function(data) {
		$scope.entries = data.results;
		$scope.currentPageForEntries = data.currentPage;
		$scope.nextPageForEntries = data.nextPage;
	}).catch(function() {
		$scope.entries = {};
	});
	
	$scope.showMoreEntries = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.getBlogEntries($routeParams.id, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.entries.push(data.results[j]);
				}
				$scope.currentPageForEntries = data.currentPage;
				$scope.nextPageForEntries = data.nextPage;
			});
		}
	};
	
	$scope.createBlogEntry = function(entry) {
		userService.createBlogEntry($routeParams.id, authSessionService.getToken(), entry).then(function(data) {
			$scope.entries.unshift(data);
		});	
	};
	
	$scope.createBlogEntryComment = function(id, comment) {
		userService.createBlogEntryComment(id, authSessionService.getToken(), comment).then(function(data) {
			var z = 0;
			while (z < $scope.entries.length) {
				if ($scope.entries[z]['id'] == data.entry.id) {
					$scope.entries[z]['comments'].unshift(data);
				}
				z++;
			} 
		});
	};
	
	$scope.showMoreComments = function(id, currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.getBlogEntryComments(id, nextPage).then(function(data) {
				var z = 0;
				while (z < $scope.entries.length) {
					if ($scope.entries[z]['id'] == id) {
						for (var j = 0; j < data.results.length; j++) {
							$scope.entries[z]['comments'].push(data.results[j]);
						}
						$scope.entries[z]['currentPageForComments'] = data.currentPage;
						$scope.entries[z]['nextPageForComments'] = data.nextPage;
					}
					z++;
				}
			}).catch(function() {});
		}
	};
	
	$scope.isSamePerson = function() {
		return (authSessionService.getId() == $routeParams.id) ? true : false;
	};
	j(':file').filestyle();
	$scope.image = { id : 0, mimetype : undefined, contentBase64Encoded : null };	
	$scope.uploadFile = function() {
        imageService.upload($scope.myFile, authSessionService.getToken()).then(function(data) {
        	if ((data !== null) && (data.id > 0)) {
        		$scope.image.id = data.id;
        		$scope.image.mimetype = data.mimetype;
        		$scope.image.contentBase64Encoded = data.contentBase64Encoded;
        	}
        });
    };  
    $scope.setAvatar = function(imageId) {
    	if (imageId > 0) {
    		userService.setPhoto($routeParams.id, authSessionService.getToken(), imageId).then(function(data) {
    			$scope.user = data;
    			var currentPageTemplate = $route.current.templateUrl;
    			$templateCache.remove(currentPageTemplate);
    			$route.reload();
    		}).catch(function() { });
    	}
    };
    $scope.discard = function(imageId) {
    	if (imageId > 0) {
    		imageService.discard(authSessionService.getToken(), imageId).then(function(response) {
    			$scope.image = { id : 0, mimetype : undefined, contentBase64Encoded : null };
    		}).catch(function() { });
    	}
    };
    
    j('#msgModal').on('shown.bs.modal', function () {
		j('#messageText').focus();
	});
	$scope.body = '';
	$scope.msgStatus = '';
	$scope.send = function(recipientId, body) {
		messageService.send(authSessionService.getToken(), authSessionService.getId(), recipientId, body).then(function(data) {
			$scope.body = '';
			j('#msgModal').modal('hide');
			$scope.msgStatus = ((data !== null) && (data.id > 0)) ? 'Message has been sent.' : 'Message has not been sent.';
		}).catch(function() {
			$scope.msgStatus = 'Message has not been sent because of internal error.';
		});
	};
}]);
app.controller('peopleBlogEntryController', ['$http', '$scope', '$route', '$routeParams', '$templateCache', 'userService', 'messageService', 'locationService', 'imageService', 'authSessionService', function($http, $scope, $route, $routeParams, $templateCache, userService, messageService, locationService, imageService, authSessionService) {
	userService.getBlogEntry($routeParams.entryId, authSessionService.getToken()).then(function(data) {
		$scope.entry = data;
		userService.getBlogEntryComments($routeParams.entryId, 1).then(function(comments) {
			$scope.entry.comments = comments.results;
			$scope.entry.currentPageForComments = comments.currentPage;
			$scope.entry.nextPageForComments = comments.nextPage;
		}).catch(function() {
			$scope.entry.comments = [];
		});
	}).catch(function() {
		$scope.entry = {};
	});
	
	$scope.showMoreComments = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			userService.getBlogEntryComments($routeParams.id, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.entry.comments.push(data.results[j]);
				}
				$scope.entry.currentPageForComments = data.currentPage;
				$scope.entry.nextPageForComments = data.nextPage;
			}).catch(function() {});
		}
	};
	
	$scope.createBlogEntryComment = function(id, comment) {
		userService.createBlogEntryComment(id, authSessionService.getToken(), comment).then(function(data) {
			$scope.entry.comments.unshift(data); 
		});
	};
	
	$scope.isSamePerson = function() {
		return (authSessionService.getId() == $routeParams.id) ? true : false;
	};
}]);
app.controller('peopleEditController', ['$scope', '$routeParams', 'userService', 'languageService', 'locationService', 'authSessionService', function($scope, $routeParams, userService, languageService, locationService, authSessionService) {
	userService.getById($routeParams.id).then(function(data) {
		$scope.user = data;
		$scope.days = $scope.initDays();
		$scope.months = $scope.initMonths();
		$scope.years = $scope.initYears();
		$scope.getDivisions(data.country);
	}).catch(function() {
		$scope.user = {};
	});
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear()) - 90;
		while(year <= (new Date().getFullYear()) - 7) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data
	}).catch(function () {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	languageService.getAll().then(function(data) {
		$scope.languages = data;
	}).catch(function() {
		$scope.languages = [];
	});
	$scope.isSamePerson = function() {
		return (authSessionService.getId() == $routeParams.id) ? true : false;
	};
	$scope.update = function(user) {
		userService.update($routeParams.id, authSessionService.getToken(), user).then(function(data) {
			$scope.user = data;
		}).catch(function() {
			$scope.user = user;
		});
	};
	
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.user.city = city.name;
			$scope.user.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	
	$scope.levelOptions = [ 
	    { value : 0, label : locale[$routeParams.lang]['unknown'] + ' (0)' }, 
	    { value : 1, label : locale[$routeParams.lang]['novice'] + ' (1)' }, 
	    { value : 2, label : locale[$routeParams.lang]['beginner'] + ' (2)' },
	    { value : 3, label : locale[$routeParams.lang]['intermediate'] + ' (3)' },
	    { value : 4, label : locale[$routeParams.lang]['advanced'] + ' (4)' },
	    { value : 5, label : locale[$routeParams.lang]['professional'] + ' (5)' }
	];
	$scope.levelShiftOptions = [
	    { value : 0, label : '+/- 0' },
	    { value : 1, label : '+/- 0' },
	    { value : 2, label : '+/- 0' },
	    { value : 3, label : '+/- 0' }
	];
}]);
app.controller('messagesListController', ['$scope', 'messageService', 'userService', 'authSessionService', function($scope, messageService, userService, authSessionService) {
	messageService.getDialogues(authSessionService.getToken(), 1).then(function(data) {
		$scope.dialogues = data.results;
		$scope.currentPageForDialogues = data.currentPage;
		$scope.nextPageForDialogues = data.nextPage;
	}).catch(function() {
		$scope.dialogues = [];
	});
	
	$scope.showMoreDialogues = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			messageService.getDialogues(authSessionService.getToken(), nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.dialogues.push(data.results[j]);
				}
				$scope.currentPageForDialogues = data.currentPage;
				$scope.nextPageForDialogues = data.nextPage;
			}).catch(function() {});
		}
	};
}]);
app.controller('messagesDetailsController', ['$scope', '$routeParams', 'messageService', 'userService', 'authSessionService', function($scope, $routeParams, messageService, userService, authSessionService) {
	messageService.getMessages($routeParams.dialogueId, authSessionService.getToken(), 1).then(function(data) {
		$scope.messages = data.results;
		$scope.currentPageForMessages = data.currentPage;
		$scope.nextPageForMessages = data.nextPage;
	}).catch(function() {
		$scope.messages = [];
	});
	$scope.showMoreMessages = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			messageService.getMessages($routeParams.dialogueId, authSessionService.getToken(), nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.messages.push(data.results[j]);
				}
				$scope.currentPageForMessages = data.currentPage;
				$scope.nextPageForMessages = data.nextPage;
			}).catch(function() {});
		}
	};
	$scope.sendFromDialogues = function(body) {
		senderId = authSessionService.getId();
		dialogueId = $routeParams.dialogueId;
		ids = dialogueId.split('-');
		recipientId = (senderId != ids[0]) ? ids[0] : ids[1];
		messageService.send(authSessionService.getToken(), senderId, recipientId, body).then(function(response) {
			$scope.body = '';
			if ((data !== null) && (data.id > 0)) {
				$scope.messages.unshift(data);
			}
		}).catch(function() { });
	};
}]);
app.controller('challengeMatchListController', ['$scope', '$route', 'challengeMatchService', 'userService', function($scope, $route, challengeMatchService, userService) {
	challengeMatchService.getAll('', 1).then(function(data) {
		$scope.challenges = data.results;
		$scope.currentPageForChallenges = data.currentPage;
		$scope.nextPageForChallenges = data.nextPage;
	}).catch(function() {
		$scope.challenges = [];
	});
	$scope.$route = $route;
	
	$scope.showMoreChallenges = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			challengeMatchService.getAll('', nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.challenges.push(data.results[j]);
				}
				$scope.currentPageForChallenges = data.currentPage;
				$scope.nextPageForChallenges = data.nextPage;
			}).catch(function() {});
		}
	}
}]);
app.controller('challengeMatchDetailsController', ['$scope', '$route', '$routeParams', 'challengeMatchService', 'userService', 'authSessionService', function($scope, $route, $routeParams, challengeMatchService, userService, authSessionService) {
	challengeMatchService.getById($routeParams.challengeId).then(function(data) {
		$scope.challenge = data;
	}).catch(function() {
		$scope.challenge = [];
	});
	$scope.accept = function() {
		challengeMatchService.accept($routeParams.challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { });
	};
	$scope.reject = function() {
		challengeMatchService.reject($routeParams.challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { });
	};
	$scope.finish = function() {
		if (confirm('Are you sure you want to submit the score and finish the match? This cannot be undone.')) {
			challengeMatchService.finish($routeParams.challengeId, authSessionService.getToken()).then(function(data) {
				$scope.challenge = data;
				$route.reload();
			}).catch(function() { });
		}
	};
	$scope.update = function(challenge) {
		challengeMatchService.update($routeParams.challengeId, authSessionService.getToken(), challenge).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { });
	};
}]);
app.controller('newChallengeMatchController', ['$scope', '$routeParams', 'challengeMatchService', 'authSessionService', 'locationService', 'userService', function($scope, $routeParams, challengeMatchService, authSessionService, locationService, userService) {
	userService.getById(authSessionService.getId()).then(function(data) {
		$scope.firstPlayer = data;
		$scope.getDivisions(data.country);
		$scope.challenge = { 
			firstPlayerId : data.id,
			secondPlayerId : $routeParams.playerId, 
			startDateDay : 0, 
			startDateMonth : 0, 
			startDateYear : (new Date().getFullYear()), 
			country : data.country, 
			state : data.state, 
			city : data.city,
			cityGeonameId : data.cityGeonameId, 
			address : '',
			description : '', 
			surfaceType : ''
		};
	}).catch(function() {
		$scope.firstPlayer = [];
	});
	userService.getById($routeParams.playerId).then(function(data) {
		$scope.secondPlayer = data;
	}).catch(function() {
		$scope.secondPlayer = [];
	});
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data;
	}).catch(function() {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.challenge.city = city.name;
			$scope.challenge.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	$scope.create = function(challenge) {
		challengeMatchService.create(challenge, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
		}).catch(function() { });
	}
	$scope.isSamePerson = function() {
		return (authSessionService.getId() == $routeParams.playerId) ? true : false;
	}
}]);
app.controller('playerChallengeMatchController', ['$scope', '$route', '$routeParams', 'challengeMatchService', 'userService', 'authSessionService', function($scope, $route, $routeParams, challengeMatchService, userService, authSessionService) {
	challengeMatchService.getAll('&firstPlayer=' + $routeParams.playerId, 1).then(function(data) {
		$scope.challenges = data.results;
		$scope.currentPageForChallenges = data.currentPage;
		$scope.nextPageForChallenges = data.nextPage;
	}).catch(function() {
		$scope.challenges = [];
	});
	
	$scope.showMoreChallenges = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			challengeMatchService.getAll('&firstPlayer=' + $routeParams.playerId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.challenges.push(data.results[j]);
				}
				$scope.currentPageForChallenges = data.currentPage;
				$scope.nextPageForChallenges = data.nextPage;
			}).catch(function() {});
		}
	}
	
	$scope.accept = function(challengeId) {
		challengeMatchService.accept(challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { $route.reload(); });
	};
	$scope.reject = function(challengeId) {
		challengeMatchService.reject(challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { $route.reload(); });
	};
}]);

app.controller('openChallengeListController', ['$scope', '$route', 'openChallengeService', 'userService', 'authSessionService', function($scope, $route, openChallengeService, userService, authSessionService) {
	openChallengeService.getAll('', 1).then(function(data) {
		$scope.challenges = data.results;
		$scope.currentPageForChallenges = data.currentPage;
		$scope.nextPageForChallenges = data.nextPage;
	}).catch(function() {
		$scope.challenges = [];
	});
	$scope.$route = $route;
	
	$scope.showMoreChallenges = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			openChallengeService.getAll('', nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.challenges.push(data.results[j]);
				}
				$scope.currentPageForChallenges = data.currentPage;
				$scope.nextPageForChallenges = data.nextPage;
			}).catch(function() {});
		}
	}
	$scope.accept = function(challengeId) {
		openChallengeService.accept(challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { $route.reload(); });
	};
}]);
app.controller('openChallengeDetailsController', ['$scope', '$route', '$routeParams', 'openChallengeService', 'userService', 'authSessionService', function($scope, $route, $routeParams, openChallengeService, userService, authSessionService) {
	openChallengeService.getById($routeParams.challengeId).then(function(data) {
		$scope.challenge = data;
	}).catch(function() {
		$scope.challenge = [];
	});
	$scope.accept = function() {
		openChallengeService.accept($routeParams.challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { });
	};

	$scope.update = function(challenge) {
		openChallengeService.update($routeParams.challengeId, authSessionService.getToken(), challenge).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { });
	};
}]);
app.controller('newOpenChallengeController', ['$scope', '$routeParams', 'openChallengeService', 'authSessionService', 'locationService', 'userService', function($scope, $routeParams, openChallengeService, authSessionService, locationService, userService) {
	userService.getById(authSessionService.getId()).then(function(data) {
		$scope.firstPlayer = data;
		$scope.getDivisions(data.country);
		$scope.challenge = { 
			firstPlayerId : data.id,
			startDateDay : 0, 
			startDateMonth : 0, 
			startDateYear : (new Date().getFullYear()), 
			country : data.country, 
			state : data.state, 
			city : data.city,
			cityGeonameId : data.cityGeonameId, 
			address : '',
			description : '',
			surfaceType : ''
		};
	}).catch(function() {
		$scope.firstPlayer = [];
	});
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data;
	}).catch(function() {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.challenge.city = city.name;
			$scope.challenge.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	$scope.create = function(challenge) {
		openChallengeService.create(challenge, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
		}).catch(function() { });
	}
	$scope.isSamePerson = function() {
		return (authSessionService.getId() == $routeParams.playerId) ? true : false;
	}
}]);
app.controller('playerOpenChallengeController', ['$scope', '$route', '$routeParams', 'openChallengeService', 'userService', 'authSessionService', function($scope, $route, $routeParams, openChallengeService, userService, authSessionService) {
	openChallengeService.getAll('&firstPlayer=' + $routeParams.playerId, 1).then(function(data) {
		$scope.challenges = data.results;
		$scope.currentPageForChallenges = data.currentPage;
		$scope.nextPageForChallenges = data.nextPage;
	}).catch(function() {
		$scope.challenges = [];
	});
	
	$scope.showMoreChallenges = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			openChallengeService.getAll('&firstPlayer=' + $routeParams.playerId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.challenges.push(data.results[j]);
				}
				$scope.currentPageForChallenges = data.currentPage;
				$scope.nextPageForChallenges = data.nextPage;
			}).catch(function() {});
		}
	}
	$scope.accept = function(challengeId) {
		openChallengeService.accept(challengeId, authSessionService.getToken()).then(function(data) {
			$scope.challenge = data;
			$route.reload();
		}).catch(function() { $route.reload(); });
	};
}]);
app.controller('manageLeagueMembersController', ['$scope', '$route', '$routeParams', 'leagueService', 'authSessionService', function($scope, $route, $routeParams, leagueService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	leagueService.getAcceptedMembers($routeParams.leagueId, 1).then(function(data) {
		$scope.members = data.results;
		$scope.currentPageForMembers = data.currentPage;
		$scope.nextPageForMembers = data.nextPage;
	}).catch(function() {
		$scope.members = [];
	});
	
	$scope.showMoreMembers = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getAcceptedMembers($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.members.push(data.results[j]);
				}
				$scope.currentPageForMembers = data.currentPage;
				$scope.nextPageForMembers = data.nextPage;
			}).catch(function() {});
		}
	};
	
	leagueService.getDeletedMembers($routeParams.leagueId, 1).then(function(data) {
		$scope.deletedMembers = data.results;
		$scope.currentPageForDeletedMembers = data.currentPage;
		$scope.nextPageForDeletedMembers = data.nextPage;
	}).catch(function() {
		$scope.deletedMembers = [];
	});
	
	$scope.showMoreDeletedMembers = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getDeletedMembers($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.deletedMembers.push(data.results[j]);
				}
				$scope.currentPageForDeletedMembers = data.currentPage;
				$scope.nextPageForDeletedMembers = data.nextPage;
			}).catch(function() {});
		}
	};
	
	$scope.deleteMember = function(userId) {
		leagueService.deleteMember($routeParams.leagueId, userId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	
	$scope.restoreMember = function(userId) {
		leagueService.restoreMember($routeParams.leagueId, userId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
	$scope.isSamePerson = function(userId, personId) {
		return (userId == personId) ? true : false;
	};
}]);
app.controller('manageLeagueRequestsController', ['$scope', '$route', '$routeParams', 'leagueService', 'authSessionService', function($scope, $route, $routeParams, leagueService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	leagueService.getMembersRequests($routeParams.leagueId, 1).then(function(data) {
		$scope.members = data.results;
		$scope.currentPageForMembers = data.currentPage;
		$scope.nextPageForMembers = data.nextPage;
	}).catch(function() {
		$scope.members = [];
	});
	
	$scope.showMoreMembers = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getMembersRequests($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.members.push(data.results[j]);
				}
				$scope.currentPageForMembers = data.currentPage;
				$scope.nextPageForMembers = data.nextPage;
			}).catch(function() {});
		}
	};
	
	leagueService.getRejectedMembers($routeParams.leagueId, 1).then(function(data) {
		$scope.rejectedMembers = data.results;
		$scope.currentPageForRejectedMembers = data.currentPage;
		$scope.nextPageForRejectedMembers = data.nextPage;
	}).catch(function() {
		$scope.rejectedMembers = [];
	});
	
	$scope.showMoreRejectedMembers = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getRejectedMembers($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.rejectedMembers.push(data.results[j]);
				}
				$scope.currentPageForRejectedMembers = data.currentPage;
				$scope.nextPageForRejectedMembers = data.nextPage;
			}).catch(function() {});
		}
	};
	
	$scope.acceptRequest = function(userId) {
		leagueService.acceptRequest($routeParams.leagueId, userId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	
	$scope.rejectRequest = function(userId) {
		leagueService.rejectRequest($routeParams.leagueId, userId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
	$scope.isSamePerson = function(userId, personId) {
		return (userId == personId) ? true : false;
	};
}]);

app.controller('manageLeagueTournamentsController', ['$scope', '$route', '$routeParams', 'tournamentService', 'leagueService', 'authSessionService', function($scope, $route, $routeParams, tournamentService, leagueService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	tournamentService.getAll('&deleted=true&league=' + $routeParams.leagueId, 1).then(function(data) {
		$scope.tournaments = data.results;
		$scope.currentPageForTournaments = data.currentPage;
		$scope.nextPageForTournaments = data.nextPage;
	}).catch(function() {
		$scope.tournaments = [];
	});
	$scope.showMoreTournaments = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getAll('&deleted=true&league=' + $routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.tournaments.push(data.results[j]);
				}
				$scope.currentPageForTournaments = data.currentPage;
				$scope.nextPageForTournaments = data.nextPage;
			}).catch(function() {});
		}
	};
	$scope.deleteTournament = function(id) {
		tournamentService.deleteTournament(id, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	$scope.restoreTournament = function(id) {
		tournamentService.restoreTournament(id, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
}]);
app.controller('manageLeagueCreateTournamentController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
		$scope.tournament = {
			name : '',
			description : '',
			startDateDay : 0, 
			startDateMonth : 0, 
			startDateYear : (new Date().getFullYear()),
			endDateDay : 0, 
			endDateMonth : 0, 
			endDateYear : (new Date().getFullYear()),
			numberOfPlayers : 16, 
			targetLevel : 3, 
			levelShift : 1,
			leagueId : data.id
		};
	}).catch(function() {
		$scope.league = {};
	});
	$scope.create = function(tournament) {
		tournamentService.create(tournament, $routeParams.leagueId, authSessionService.getToken()).then(function(data) {
			$scope.tournament = data;
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	
	$scope.levelOptions = [ 
	    { value : 0, label : locale[$routeParams.lang]['unknown'] + ' (0)' }, 
	    { value : 1, label : locale[$routeParams.lang]['novice'] + ' (1)' }, 
	    { value : 2, label : locale[$routeParams.lang]['beginner'] + ' (2)' },
	    { value : 3, label : locale[$routeParams.lang]['intermediate'] + ' (3)' },
	    { value : 4, label : locale[$routeParams.lang]['advanced'] + ' (4)' },
	    { value : 5, label : locale[$routeParams.lang]['professional'] + ' (5)' }
	];
	$scope.levelShiftOptions = [
	    { value : 0, label : '+/- 0' },
	    { value : 1, label : '+/- 0' },
	    { value : 2, label : '+/- 0' },
	    { value : 3, label : '+/- 0' }
	];
}]);
app.controller('manageLeagueEditTournamentController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	$scope.update = function(tournament) {
		tournamentService.update($routeParams.tournamentId, tournament, authSessionService.getToken()).then(function(data) {
			$scope.tournament = data;
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.tournament) && angular.isDefined($scope.tournament.league) && 
			(
				(authSessionService.getId() == $scope.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.tournament.league.managerId)
			)
		) ? true : false;
			
	};
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	
	$scope.levelOptions = [ 
	    { value : 0, label : locale[$routeParams.lang]['unknown'] + ' (0)' }, 
	    { value : 1, label : locale[$routeParams.lang]['novice'] + ' (1)' }, 
	    { value : 2, label : locale[$routeParams.lang]['beginner'] + ' (2)' },
	    { value : 3, label : locale[$routeParams.lang]['intermediate'] + ' (3)' },
	    { value : 4, label : locale[$routeParams.lang]['advanced'] + ' (4)' },
	    { value : 5, label : locale[$routeParams.lang]['professional'] + ' (5)' }
	];
	$scope.levelShiftOptions = [
	    { value : 0, label : '+/- 0' },
	    { value : 1, label : '+/- 0' },
	    { value : 2, label : '+/- 0' },
	    { value : 3, label : '+/- 0' }
	];
}]);
app.controller('createLeagueTournamentMatchController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'locationService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, locationService, tournamentService) {
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
		$scope.getDivisions(data.supervisor.country);
		$scope.match = { 
			tournamentId : data.id, 
			firstPlayerId : '',
			secondPlayerId : '', 
			startDateDay : 0, 
			startDateMonth : 0, 
			startDateYear : (new Date().getFullYear()), 
			country : data.supervisor.country, 
			state : data.supervisor.state, 
			city : data.supervisor.city,
			cityGeonameId : data.supervisor.cityGeonameId, 
			address : '',
			description : '', 
			surfaceType : '', 
			stage : data.numberOfPlayers
		};
	}).catch(function() {
		$scope.tournament = {};
	});
	
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data;
	}).catch(function() {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.match.city = city.name;
			$scope.match.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	$scope.initStages = function() {
		stagesArray = [];
		st = 64;
		while (st > 1) {
			stagesArray.push({value : st, label : '1/' + st});
			st = st / 2;
		}
		stagesArray.push({value : 1, label : 'Finale'});
		return stagesArray;
	};
	$scope.stages = $scope.initStages();
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	$scope.createMatch = function(match) {
		tournamentService.createMatch($routeParams.tournamentId, authSessionService.getToken(), match).then(function(data) {
			$scope.match = data;
		}).catch(function() { });
	};
	$scope.searchParticipantsById = function(id) {
		return tournamentService.searchParticipantsById($routeParams.tournamentId, id, 1).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.tournament) && angular.isDefined($scope.tournament.league) && 
			(
				(authSessionService.getId() == $scope.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.tournament.league.managerId)
			)
		) ? true : false;
			
	};
}]);
app.controller('editLeagueTournamentMatchController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'locationService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, locationService, tournamentService) {
	tournamentService.getMatch($routeParams.matchId).then(function(data) {
		$scope.match = data;
		$scope.getDivisions(data.country);
	}).catch(function() {
		$scope.match = {};
	});
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data;
	}).catch(function() {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.match.city = city.name;
			$scope.match.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	$scope.initStages = function() {
		stagesArray = [];
		st = 64;
		while (st > 1) {
			stagesArray.push({value : st, label : '1/' + st});
			st = st / 2;
		}
		stagesArray.push({value : 1, label : 'Finale'});
		return stagesArray;
	};
	$scope.stages = $scope.initStages();
	$scope.initDays = function() {
		daysArray = [];
		day = 1;
		while(day <= 31) {
			daysArray.push(day);
			day++;
		}
		return daysArray;
	};
	$scope.days = $scope.initDays();
	$scope.initMonths = function() {
		monthsArray = [];
		month = 1;
		while(month <= 12) {
			monthsArray.push(month);
			month++;
		}
		return monthsArray;
	};
	$scope.months = $scope.initMonths();
	$scope.initYears = function() {
		yearsArray = [];
		year = (new Date().getFullYear());
		while(year <= (new Date().getFullYear()) + 1) {
			yearsArray.push(year);
			year++;
		}
		return yearsArray;
	};
	$scope.years = $scope.initYears();
	$scope.updateMatch = function(match) {
		tournamentService.updateMatch($routeParams.matchId, authSessionService.getToken(), match).then(function(data) {
			$scope.match = data;
		}).catch(function() { });
	};
	$scope.searchParticipantsById = function(id) {
		return tournamentService.searchParticipantsById($routeParams.tournamentId, id, 1).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.match) && angular.isDefined($scope.match.tournament) && angular.isDefined($scope.match.tournament.league) && 
			(
				(authSessionService.getId() == $scope.match.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.match.tournament.league.managerId) ||
				(authSessionService.getId() == $scope.match.tournament.supervisorId)
			)
		) ? true : false;		
	};
	$scope.finishMatch = function() {
		tournamentService.finishMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.approveMatch = function() {
		tournamentService.approveMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.deleteMatch = function() {
		tournamentService.deleteMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.restoreMatch = function() {
		tournamentService.restoreMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
}]);
app.controller('manageLeagueTournamentMatchesController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.tournament) && angular.isDefined($scope.tournament.league) && 
			(
				(authSessionService.getId() == $scope.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.tournament.league.managerId)
			)
		) ? true : false;
			
	};
	tournamentService.getMatches($routeParams.tournamentId, 1, '').then(function(data) {
		$scope.matches = data.results;
		$scope.currentPageForMatches = data.currentPage;
		$scope.nextPageForMatches = data.nextPage;
	}).catch(function() {
		$scope.matches = [];
	});	
	$scope.showMoreMatches = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getMatches($routeParams.tournamentId, nextPage, '').then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.matches.push(data.results[j]);
				}
				$scope.currentPageForMatches = data.currentPage;
				$scope.nextPageForMatches = data.nextPage;
			}).catch(function() {});
		}
	};
	$scope.finishMatch = function(id) {
		tournamentService.finishMatch(id, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.matches !== null)) {
				var m = 0;
				while (p < $scope.matches.length) {
					if ($scope.matches[m]['id'] == data.id) $scope.matches[m] = data;
					m++;
				}
			}
		});
	};
	$scope.approveMatch = function(id) {
		tournamentService.approveMatch(id, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.matches !== null)) {
				var m = 0;
				while (p < $scope.matches.length) {
					if ($scope.matches[m]['id'] == data.id) $scope.matches[m] = data;
					m++;
				}
			}
		});
	};
	$scope.deleteMatch = function(id) {
		tournamentService.deleteMatch(id, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.matches !== null)) {
				var m = 0;
				while (p < $scope.matches.length) {
					if ($scope.matches[m]['id'] == data.id) $scope.matches[m] = data;
					m++;
				}
			}
		});
	};
	$scope.restoreMatch = function(id) {
		tournamentService.restoreMatch(id, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.matches !== null)) {
				var m = 0;
				while (p < $scope.matches.length) {
					if ($scope.matches[m]['id'] == data.id) $scope.matches[m] = data;
					m++;
				}
			}
		});
	};
}]);
app.controller('manageLeagueTournamentParticipantsController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.tournament) && angular.isDefined($scope.tournament.league) && 
			(
				(authSessionService.getId() == $scope.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.tournament.league.managerId)
			)
		) ? true : false;
			
	};
	tournamentService.getParticipants($routeParams.tournamentId, 1).then(function(data) {
		$scope.participants = data.results;
		$scope.currentPageForParticipants = data.currentPage;
		$scope.nextPageForParticipants = data.nextPage;
	}).catch(function() {
		$scope.participants = [];
	});	
	$scope.showMoreParticipants = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getParticipants($routeParams.tournamentId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.participants.push(data.results[j]);
				}
				$scope.currentPageForParticipants = data.currentPage;
				$scope.nextPageForParticipants = data.nextPage;
			}).catch(function() {});
		}
	};
	$scope.confirmParticipant = function(id) {
		tournamentService.confirmParticipant(id, $routeParams.tournamentId, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.participants !== null)) {
				var p = 0;
				while (p < $scope.participants.length) {
					if ($scope.participants[p]['id'] == data.id) $scope.participants[p] = data;
					p++;
				}
			}
		});
	};
	$scope.unconfirmParticipant = function(id) {
		tournamentService.unconfirmParticipant(id, $routeParams.tournamentId, authSessionService.getToken()).then(function(data) {
			if ((data != null) && ($scope.participants !== null)) {
				var p = 0;
				while (p < $scope.participants.length) {
					if ($scope.participants[p]['id'] == data.id) $scope.participants[p] = data;
					p++;
				}
			}
		});
	};
	$scope.deleteParticipant = function(id) {
		tournamentService.deleteParticipant(id, $routeParams.tournamentId, authSessionService.getToken()).then(function() {
			$route.reload();
		});
	};
}]);
app.controller('leagueTournamentListController', ['$scope', '$route', '$routeParams', 'tournamentService', 'leagueService', 'authSessionService', function($scope, $route, $routeParams, tournamentService, leagueService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	tournamentService.getAll('&league=' + $routeParams.leagueId, 1).then(function(data) {
		$scope.tournaments = data.results;
		$scope.currentPageForTournaments = data.currentPage;
		$scope.nextPageForTournaments = data.nextPage;
	}).catch(function() {
		$scope.tournaments = [];
	});
	$scope.showMoreTournaments = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getAll('&league=' + $routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.tournaments.push(data.results[j]);
				}
				$scope.currentPageForTournaments = data.currentPage;
				$scope.nextPageForTournaments = data.nextPage;
			}).catch(function() {});
		}
	};
}]);
app.controller('tournamentsController', ['$scope', '$route', '$routeParams', 'tournamentService', 'leagueService', 'authSessionService', function($scope, $route, $routeParams, tournamentService, leagueService, authSessionService) {
	tournamentService.getAll('', 1).then(function(data) {
		$scope.tournaments = data.results;
		$scope.currentPageForTournaments = data.currentPage;
		$scope.nextPageForTournaments = data.nextPage;
	}).catch(function() {
		$scope.tournaments = [];
	});
	$scope.showMoreTournaments = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getAll('', nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.tournaments.push(data.results[j]);
				}
				$scope.currentPageForTournaments = data.currentPage;
				$scope.nextPageForTournaments = data.nextPage;
			}).catch(function() {});
		}
	};
}]);
app.controller('tournamentMatchesController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getById(0).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	tournamentService.getMatches(0, 1, '&deleted=false').then(function(data) {
		$scope.matches = data.results;
		$scope.currentPageForMatches = data.currentPage;
		$scope.nextPageForMatches = data.nextPage;
	}).catch(function() {
		$scope.matches = [];
	});	
	$scope.showMoreMatches = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getMatches(0, nextPage, '&deleted=false').then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.matches.push(data.results[j]);
				}
				$scope.currentPageForMatches = data.currentPage;
				$scope.nextPageForMatches = data.nextPage;
			}).catch(function() {});
		}
	};
}]);
app.controller('leagueTournamentMatchesListController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.tournament) && angular.isDefined($scope.tournament.league) && 
			(
				(authSessionService.getId() == $scope.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.tournament.league.managerId)
			)
		) ? true : false;
			
	};
	tournamentService.getMatches($routeParams.tournamentId, 1, '&deleted=false').then(function(data) {
		$scope.matches = data.results;
		$scope.currentPageForMatches = data.currentPage;
		$scope.nextPageForMatches = data.nextPage;
	}).catch(function() {
		$scope.matches = [];
	});	
	$scope.showMoreMatches = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getMatches($routeParams.tournamentId, nextPage, '&deleted=false').then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.matches.push(data.results[j]);
				}
				$scope.currentPageForMatches = data.currentPage;
				$scope.nextPageForMatches = data.nextPage;
			}).catch(function() {});
		}
	};
}]);
app.controller('leagueTournamentMatchDetailsController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	tournamentService.getMatch($routeParams.matchId).then(function(data) {
		$scope.match = data;
	}).catch(function() {
		$scope.match = {};
	});
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.match) && angular.isDefined($scope.match.tournament) && angular.isDefined($scope.match.tournament.league) && 
			(
				(authSessionService.getId() == $scope.match.tournament.league.creatorId) || 
				(authSessionService.getId() == $scope.match.tournament.league.managerId) ||
				(authSessionService.getId() == $scope.match.tournament.supervisorId)
			)
		) ? true : false;			
	};
	$scope.hasPermissionToFinishMatch = function() {
		return (
			angular.isDefined($scope.match) && 
			(
				(authSessionService.getId() == $scope.match.firstPlayerId) || 
				(authSessionService.getId() == $scope.match.secondPlayerId)
			)
		) ? true : false;			
	};
	$scope.finishMatch = function() {
		tournamentService.finishMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.approveMatch = function() {
		tournamentService.approveMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.deleteMatch = function() {
		tournamentService.deleteMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
	$scope.restoreMatch = function() {
		tournamentService.deleteMatch($routeParams.matchId, authSessionService.getToken()).then(function(data) {
			$scope.match = data;
		});
	};
}]);
app.controller('leagueTournamentParticipantsListController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});
	tournamentService.getParticipants($routeParams.tournamentId, 1).then(function(data) {
		$scope.participants = data.results;
		$scope.currentPageForParticipants = data.currentPage;
		$scope.nextPageForParticipants = data.nextPage;
	}).catch(function() {
		$scope.participants = [];
	});	
	$scope.showMoreParticipants = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			tournamentService.getParticipants($routeParams.tournamentId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.participants.push(data.results[j]);
				}
				$scope.currentPageForParticipants = data.currentPage;
				$scope.nextPageForParticipants = data.nextPage;
			}).catch(function() {});
		}
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
}]);
app.controller('leagueTournamentDetailsController', ['$scope', '$route', '$routeParams', 'authSessionService', 'leagueService', 'tournamentService', function($scope, $route, $routeParams, authSessionService, leagueService, tournamentService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
	}).catch(function() {
		$scope.league = {};
	});
	tournamentService.getById($routeParams.tournamentId).then(function(data) {
		$scope.tournament = data;
	}).catch(function() {
		$scope.tournament = {};
	});	
	if (authSessionService.getId() > 0) {
		tournamentService.isParticipant(authSessionService.getId(), $routeParams.tournamentId).then(function(data) {
			$scope.isParticipant = data;
		});
		tournamentService.isConfirmedParticipant(authSessionService.getId(), $routeParams.tournamentId).then(function(data) {
			$scope.isConfirmedParticipant = data;
		});
	}
	$scope.participate = function() {
		tournamentService.createParticipant(authSessionService.getId(), $routeParams.tournamentId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		});
	};
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
}]);
app.controller('leagueListController', ['$scope', '$route', 'locationService', 'leagueService', function($scope, $route, locationService, leagueService) {
	$scope.nearby = false;
	leagueService.search('&nearby=' + $scope.nearby, 1).then(function(data) {
		$scope.leagues = data.results;
		$scope.currentPageForLeagues = data.currentPage;
		$scope.nextPageForLeagues = data.nextPage;
		$scope.nearby = data.nearby;
	}).catch(function() {
		$scope.leagues = [];
	});
	
	$scope.$route = $route;
	
	$scope.showMoreLeagues = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.search('&nearby=' + $scope.nearby, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.leagues.push(data.results[j]);
				}
				$scope.currentPageForLeagues = data.currentPage;
				$scope.nextPageForLeagues = data.nextPage;
				$scope.nearby = data.nearby;
			}).catch(function() {});
		}
	};
	$scope.showNearbyOnly = function(nearby) {
		$scope.nearby = nearby;
		leagueService.search('&nearby=' + $scope.nearby, 1).then(function(data) {
			$scope.leagues = data.results;
			$scope.currentPageForLeagues = data.currentPage;
			$scope.nextPageForLeagues = data.nextPage;
			$scope.nearby = data.nearby;
		}).catch(function() {
			$scope.leagues = [];
		});
	};
}]);
app.controller('leagueBlogEntryController', ['$http', '$scope', '$route', '$routeParams', 'leagueService', 'userService', 'authSessionService', function($http, $scope, $route, $routeParams, leagueService, userService, authSessionService) {
	leagueService.getBlogEntry($routeParams.entryId, authSessionService.getToken()).then(function(data) {
		$scope.entry = data;
		leagueService.getBlogEntryComments($routeParams.entryId, 1).then(function(data) {
			$scope.comments =  data.results;
			$scope.currentPageForComments = data.currentPage;
			$scope.nextPageForComments = data.nextPage;
		}).catch(function() {
			$scope.comments = {};
		});
	}).catch(function() {
		$scope.entry = {};
	});
	
	$scope.showMoreComments = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getBlogEntryComments($routeParams.entryId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.comments.push(data.results[j]);
				}
				$scope.currentPageForComments = data.currentPage;
				$scope.nextPageForComments = data.nextPage;
			}).catch(function() {});
		}
	};

	$scope.createBlogEntryComment = function(comment) {
		leagueService.createBlogEntryComment($routeParams.entryId, authSessionService.getToken(), comment).then(function(data) {
			//$route.reload();
			$scope.comments.unshift(data);
		});
	};
}]);
app.controller('leagueDetailsController', ['$http', '$scope', '$route', '$routeParams', '$templateCache', 'leagueService', 'userService', 'imageService', 'locationService', 'authSessionService', function($http, $scope, $route, $routeParams, $templateCache, leagueService, userService, imageService, locationService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
		locationService.getCountryByCode(data.country, $routeParams.lang).then(function(result) {
			if (result !== null) $scope.league.country = result.name;
		});
	}).catch(function() {
		$scope.league = {};
	});
	
	leagueService.getBlogEntries($routeParams.leagueId, 1).then(function(data) {
		$scope.entries = data.results;
		$scope.currentPageForEntries = data.currentPage;
		$scope.nextPageForEntries = data.nextPage;
	}).catch(function() {
		$scope.entries = {};
	});
	
	$scope.showMoreEntries = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getBlogEntries($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.entries.push(data.results[j]);
				}
				$scope.currentPageForEntries = data.currentPage;
				$scope.nextPageForEntries = data.nextPage;
			}).catch(function() {});
		}
	};
	
	$scope.createBlogEntry = function(entry) {
		leagueService.createBlogEntry($routeParams.leagueId, authSessionService.getToken(), entry).then(function(data) {
			//$route.reload();
			$scope.entries.unshift(data);
		});	
	};
	
	
	leagueService.getAcceptedMembers($routeParams.leagueId, 1).then(function(data) {
		$scope.members = data.results;
		$scope.currentPageForMembers = data.currentPage;
		$scope.nextPageForMembers = data.nextPage;
	}).catch(function() {
		$scope.members = [];
	});
	
	$scope.showMoreMembers = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getAcceptedMembers($routeParams.leagueId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.members.push(data.results[j]);
				}
				$scope.currentPageForMembers = data.currentPage;
				$scope.nextPageForMembers = data.nextPage;
			}).catch(function() {});
		}
	};
	
	$scope.hasPermission = function() {
		return (
			angular.isDefined($scope.league) &&
			(
				(authSessionService.getId() == $scope.league.creator.id) || 
				(authSessionService.getId() == $scope.league.manager.id)
			)
		) ? true : false;
			
	};
	$scope.isSamePerson = function(userId, personId) {
		return (userId == personId) ? true : false;
	};
	$scope.membershipCheck = function(userId) {
		$scope.initAccepted(userId);
		$scope.initRequestor(userId);
		$scope.initRejected(userId);
		$scope.initDeleted(userId);
	};
	$scope.initAccepted = function(userId) {
		leagueService.isAccepted($routeParams.leagueId, userId).then(function(data) {
			$scope.isAccepted = (data !== null) ? data : false;
		}).catch(function() {
			$scope.isAccepted = false;
		});
	};
	$scope.initRequestor = function(userId) {
		leagueService.isRequestor($routeParams.leagueId, userId).then(function(data) {
			$scope.isRequestor = (data !== null) ? data : false;
		}).catch(function() {
			$scope.isRequestor = false;
		});
	};
	$scope.initRejected = function(userId) {
		leagueService.isRejected($routeParams.leagueId, userId).then(function(data) {
			$scope.isRejected = (data !== null) ? data : false;
		}).catch(function() {
			$scope.isRejected = false;
		});
	};
	$scope.initDeleted = function(userId) {
		leagueService.isDeleted($routeParams.leagueId, userId).then(function(data) {
			$scope.isDeleted = (data !== null) ? data : false;
		}).catch(function() {
			$scope.isDeleted = false;
		});
	};
	$scope.join = function() {
		leagueService.join($routeParams.leagueId, authSessionService.getToken()).then(function(data) {
			$route.reload();
		}).catch(function() { });
	};
	
	j(':file').filestyle();
	$scope.image = { id : 0, mimetype : undefined, contentBase64Encoded : null };	
	$scope.uploadFile = function(type) {
		if (type == 'logo') file = $scope.logoFile;
		else if (type == 'cover') file = $scope.coverFile;
		else file = null;
        imageService.upload(file, authSessionService.getToken()).then(function(data) {
        	if ((data !== null) && (data.id > 0)) {
        		$scope.image.id = data.id;
        		$scope.image.mimetype = data.mimetype;
        		$scope.image.contentBase64Encoded = data.contentBase64Encoded;
        	}
        });
    };  
    $scope.setLogo = function(imageId) {
    	if (imageId > 0) {
    		leagueService.setLogo($routeParams.leagueId, authSessionService.getToken(), imageId).then(function(data) {
    			$scope.league = data;
    			var currentPageTemplate = $route.current.templateUrl;
    			$templateCache.remove(currentPageTemplate);
    			$route.reload();
    		}).catch(function() { });
    	}
    };
    $scope.setCover = function(imageId) {
    	if (imageId > 0) {
    		leagueService.setCover($routeParams.leagueId, authSessionService.getToken(), imageId).then(function(data) {
    			$scope.league = data;
    			var currentPageTemplate = $route.current.templateUrl;
    			$templateCache.remove(currentPageTemplate);
    			$route.reload();
    		}).catch(function() { });
    	}
    };
    $scope.discard = function(imageId) {
    	if (imageId > 0) {
    		imageService.discard(authSessionService.getToken(), imageId).then(function(response) {
    			$scope.image = { id : 0, mimetype : undefined, contentBase64Encoded : null };
    		}).catch(function() { });
    	}
    };
}]);
app.controller('editLeagueController', ['$scope', '$routeParams', 'userService', 'leagueService', 'locationService', 'authSessionService', function($scope, $routeParams, userService, leagueService, locationService, authSessionService) {
	leagueService.getLeagueById($routeParams.leagueId).then(function(data) {
		$scope.league = data;
		$scope.getDivisions(data.country);
	}).catch(function() {
		$scope.league = {};
	});

	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data
	}).catch(function () {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};

	$scope.update = function(league) {
		if (league.open == '0') { league.open = false; } else { league.open = true; }
		leagueService.updateLeague($routeParams.leagueId, league, authSessionService.getToken()).then(function(data) {
			$scope.league = league;
		}).catch(function() {
			$scope.league = league;
		});
	};
	
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.league.city = city.name;
			$scope.league.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};
	
	$scope.searchMembersById = function(id) {
		return leagueService.searchMembersById($routeParams.leagueId, id, 1).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
}]);
app.controller('createLeagueController', ['$scope', '$routeParams', 'leagueService', 'authSessionService', 'locationService', 'userService', function($scope, $routeParams, leagueService, authSessionService, locationService, userService) {
	userService.getById(authSessionService.getId()).then(function(data) {
		$scope.user = data;
		$scope.getDivisions(data.country);
		$scope.league = { 
			creatorId : data.id,
			managerId : data.id,
			country : data.country, 
			state : data.state, 
			city : data.city,
			cityGeonameId : data.cityGeonameId, 
			name : '',
			description : '', 
			open : true
		};
	}).catch(function() {
		$scope.user = [];
		$scope.league = [];
	}) 
	
	locationService.getCountries($routeParams.lang).then(function(data) {
		$scope.countries = data;
	}).catch(function() {
		$scope.countries = [];
	});
	$scope.getDivisions = function(country) {
		locationService.getDivisions(country).then(function(data) {
			$scope.states = data;
		}).catch(function() {
			$scope.states = [];
		});
	};
	$scope.getCities = function(city, state, country) {
		return locationService.getCities(city, state, country).then(function(data) {
			return data.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onCitySelect = function(city) {
		if (city !== null) {
			$scope.league.city = city.name;
			$scope.league.cityGeonameId = city.geonamesCity.geonameId; 
		}
	};

	$scope.create = function(league) {
		if (league.open == '0') { league.open = false; } else { league.open = true; }
		leagueService.createLeague(league, authSessionService.getToken()).then(function(data) {
			$scope.league = data;
		}).catch(function() { });
	}
}]);
app.controller('membershipLeagueListController', ['$scope', '$route', '$routeParams', 'leagueService', 'userService', 'authSessionService', function($scope, $route, $routeParams, leagueService, userService, authSessionService) {
	userService.getById($routeParams.playerId).then(function(data) {
		$scope.user = data;
	}).catch(function() {
		$scope.user = [];
	});
	leagueService.getMemberLeagues($routeParams.playerId, 1).then(function(data) {
		$scope.leagues = data.results.map(function(item) { return item.league; });
		$scope.currentPageForLeagues = data.currentPage;
		$scope.nextPageForLeagues = data.nextPage;
	}).catch(function() {
		$scope.leagues = [];
	});
	$scope.$route = $route;
	$scope.showMoreLeagues = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getMemberLeagues($routeParams.playerId, nextPage).then(function(data) {
				results = data.results.map(function(item) { return item.league; });
				for (var j = 0; j < results.length; j++) {
					$scope.leagues.push(results[j]);
				}
				$scope.currentPageForLeagues = data.currentPage;
				$scope.nextPageForLeagues = data.nextPage;
			}).catch(function() {});
		}
	};
}]);

app.controller('managementLeagueListController', ['$scope', '$route', '$routeParams', 'leagueService', 'userService', 'authSessionService', function($scope, $route, $routeParams, leagueService, userService, authSessionService) {
	userService.getById($routeParams.playerId).then(function(data) {
		$scope.user = data;
	}).catch(function() {
		$scope.user = [];
	});
	leagueService.getManagedLeagues($routeParams.playerId, 1).then(function(data) {
		$scope.leagues = data.results;
		$scope.currentPageForLeagues = data.currentPage;
		$scope.nextPageForLeagues = data.nextPage;
	}).catch(function() {
		$scope.leagues = [];
	});
	$scope.$route = $route;
	$scope.showMoreLeagues = function(currentPage, nextPage) {
		if (nextPage > currentPage) {
			leagueService.getManagedLeagues($routeParams.playerId, nextPage).then(function(data) {
				for (var j = 0; j < data.results.length; j++) {
					$scope.leagues.push(data.results[j]);
				}
				$scope.currentPageForLeagues = data.currentPage;
				$scope.nextPageForLeagues = data.nextPage;
			}).catch(function() {});
		}
	};
}]);

app.controller('signoutController', ['$cookies', '$http', function($cookies, $http) {
	$http.post(baseUrl + 'auth/signout', { }).then(function(response) {
		$cookies.putObject('authSession', { id : null, token: null });
	});
}]);
app.controller('mainController', ['$scope', '$route', '$location', '$http', '$cookies', '$routeParams', 'userService', 'authSessionService', 'helperService', function($scope, $route, $location, $http, $cookies, $routeParams, userService, authSessionService, helperService) {
	$scope.newMessages = 0;
	$scope.$on('$routeChangeSuccess', function() {
		$scope.routeParams = $routeParams;
		$scope.translate = locale[($routeParams.lang !== null) ? $routeParams.lang : 'en']; 
	});
	if ($cookies.getObject('authSession') == null) { 
		$cookies.putObject('authSession', { id : null, token : null }); 
	}
	$scope.authenticate = function() {
		$http.post(apiBaseUrl + '/api/auth', $cookies.getObject('authSession'))
		.then(function(response) {
			$scope.authenticated = response.data;
			$scope.authSession = $cookies.getObject('authSession');
		});
	}
	
	$scope.credentials = { email : '', password : '' };
	$scope.signin = function(credentials) {
		$http.post(apiBaseUrl + '/api/auth/signin', credentials)
		.then(function(response) {
			if (response != null) {
				$cookies.putObject('authSession', response.data);
				if (response.data.lang !== null) {
					//$routeParams.lang = response.data.lang;
					$route.updateParams({lang : response.data.lang});
				}
			} else { $cookies.putObject('authSession', null); }
			$scope.credentials.password = '';
			$scope.authenticate();
			//$route.reload();
		});
	}
	
	$scope.signout = function() {
		$http.post(apiBaseUrl + '/api/auth/signout', { })
		.then(function(response) {
			$cookies.putObject('authSession', { id : null, token: null });
			$scope.authenticate();
		});
	}
	
	$scope.signup = function(user) {
		$http.post(apiBaseUrl + '/api/users/new', user)
		.then(function(response) {
			if ((response.data != null) && (response.data.email == user.email)) {
				$scope.signin({ email : user.email, password : user.password });
			}
		});
	}
	
	$scope.restore = function(email) {
		$http.post(apiBaseUrl + '/api/auth/restore', email)
		.then(function(response) {
			console.log(JSON.stringify(response.data));
			console.log(response.data);
		});
	}
	
	$scope.renderImage = function(image) {
		if (
			(image !== null) && angular.isDefined(image) && 
			('id' in image) && (image.id > 0) && 
			('mimetype' in image) && 
			('contentBase64Encoded' in image)
		) { 
			return 'data:' + image.mimetype + ';base64,' + image.contentBase64Encoded; 
		} else { 
			return noPhoto;
			//return 'data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=='; 
		}
    }
    
    $scope.buildLocalizedUrl = function(code) {
    	return code + $location.url().substring(3);
    }
    
    $scope.search = { user : { name : ''} };
    $scope.searchForUsers = function(name) {
    	queryString = 'name=' + name;
		return userService.search(queryString, 1).then(function(data) {
			var results = data.results;
			return results.map(function(item) {
				return item;
			});
		}).catch(function() {
			return [];
		});
	};
	$scope.onPersonSelect = function(user) {
		$location.path('/' + $routeParams.lang + '/people/' + user.id);
	};
}]);

app.controller('languagesController', ['$scope', '$http', function($scope, $http) {
	$scope.languages = { };
	$http.get(apiBaseUrl + '/api/languages')
	.then(function(response) {
		$scope.languages = response.data;
	})
}]);



