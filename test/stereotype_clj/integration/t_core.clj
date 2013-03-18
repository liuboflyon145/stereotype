(ns stereotype-clj.integration.t-core
  (:use
    [midje.sweet]
    [stereotype-clj.core]
    [stereotype-clj.integration.support]
    [stereotype-clj.integration.stereotypes]
    [korma.db]
    [korma.core]))

(namespace-state-changes [
  (around :facts (transaction ?form (rollback)))
  (before :facts (do
                   (stereotype-clj.stereotypes/reset-stereotypes)
                   (init)))])

(facts "stereotype!"
  (fact "it should raise an error on an invalid stereotype key"
   (stereotype! :made-up) => (throws Exception #":made-up"))

  (fact "it should create a record in the database with default values"
    (stereotype! :admin-users {:company "soundcloud"})

    (select-keys (first (select admin-users)) [:username :company]) => {:username "josephwilk"
                                                                        :company "soundcloud"}
    (:date_of_birth (first(select admin-users))) => #"^\d+-\d+-\d+")

  (future-fact "it should return the attributes used to create record"
    (select-keys (stereotype! :admin-users {:company "soundcloud"}) [:username :company]) => {:username "josephwilk"
                                                                                              :company "soundcloud"})

  (fact "stereotypes create their associations"
    (stereotype! :users)
    ((first (select users (with address))) :postcode ) => "1234"))

