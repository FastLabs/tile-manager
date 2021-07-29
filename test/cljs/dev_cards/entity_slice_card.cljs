(ns dev-cards.entity-slice-card
  (:require [clojure.test :refer [deftest is testing]]
            [devcards.core :refer-macros [defcard-rg defcard]]))

(def entities {"/continents" []
               "/country-by-id"})
