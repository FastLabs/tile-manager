(ns dev-cards.container-card
  (:require [reagent.core :as ra]
            [devcards.core :refer-macros [defcard-rg defcard]]))

;TODO: title is moving to the next line. Ideally the title should be truncated

(defn card-bar [title]
  [:div.border-b-2.border-gray-100
   [:div.flex.flex-row.flex-no-wrap.gap-4.justify-end
    [:div.w-full
     [:span title]]
    [:div
     [:i.fas.fa-arrow-right] 1 2 4]]])

(defn card-content [content]
  [:div content])

(defn view-card [{:keys [title content] :as  view-spec}]
  [:div.container.border-2.border-indigo-600.rounded-lg
   [card-bar  title]
   [card-content content]])



(defcard-rg
  simple
  [view-card {:title "Simple View"
              :content [:div "Simple View Content"]}])