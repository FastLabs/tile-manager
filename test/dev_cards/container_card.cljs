(ns dev-cards.container-card
  (:require [reagent.core :as ra]
            [devcards.core :refer-macros [defcard-rg defcard]]))

(defcard-rg
  simple
  [:div.container.p-4.border.border-indigo-600 "simple card"])