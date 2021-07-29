(ns dev-cards.container-card
  (:require [reagent.core :as ra]
            [slices.core :refer [slice-view view-card]]
            [devcards.core :refer-macros [defcard-rg defcard]]))


;end components
(defn gen-card [id title sample-content]
  ^{:key id}
  [view-card {:id      id
              :title   title
              :content [:div sample-content]}])

(defcard-rg
  container-vew
  [:div
   [view-card {:id                "main-card"
               :title             "main card"
               :content           [:div "main card content"]
               :action-handler-fn (fn [action view-id])
               :actions           [{:action :close
                                    :icon   ""}]}]])


(defcard-rg
  slice-view-horizontal
  [:div.container.mx-auto
   [slice-view
    [(gen-card "first-card"
               "Simple View"
               "Simple View Content")
     (gen-card "second-card"
               "Not A simple view"
               "Another view")]]])

(defcard-rg
  slice-view-vertical
  [:div.container.mx-auto
   [slice-view {:vertical? true}
    [(gen-card "first-card"
               "Simple View"
               "Simple View Content")
     (gen-card "second-card"
               "Not A simple view"
               "Another view")]]])
(defcard-rg
  composite-view
  [:div.container.mx-auto
   [slice-view [(gen-card "first card" "Entry Point" "This is where navigation starts")
                [slice-view
                 {:vertical? true}
                 [(gen-card "second card" "First Child" "Imaginary sub-content")
                  (gen-card "third card" "Second Child" "Imaginary sub-content")]]]]])
