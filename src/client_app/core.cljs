(ns ^:figwheel-hooks client-app.core
  (:require [goog.dom :as gdom]
            [reagent.dom :as rd]
            [re-frame.core :as rf]))

(rf/reg-event-db
  :initialise
  (fn [_ [_ app-config]]
    {:app-config app-config}))

(defn mount-app-element []
  (when-let [el (gdom/getElement "app")]
    (rd/render [:div "Hello World!"] el)
    (rf/dispatch-sync [:initialise {:host "1"}])))

(defn ^:export init []
  (mount-app-element))

(defn ^:after-load on-reload []
  (mount-app-element))