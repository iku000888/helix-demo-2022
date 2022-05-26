(ns iku.helix.demo
  (:require [helix.core :refer [defnc <> $]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            [reagent.core :as r]
            ["react-dom" :as rdom]))

;; define components using the `defnc` macro
(defnc greeting
  "A component which greets a user."
  [{:keys [name]}]
  ;; use helix.dom to create DOM elements
  (d/div "Hello, " (d/strong name) "!"))

(defnc helix-text [{:keys [text]}]
  (d/pre text))

(defn greeting-reagent
  "A component which greets a user."
  [{:keys [name]}]
  [:div "Hello, " [:strong name] "!"

   ;; use helix component from reagent
   ;;($ helix-text {:text name})
   ])

(defn reducer [state action]
  (case action
    :add (inc state)
    :minus (dec state)))

(defnc reducer-sample []
  (let [[curr-count dispatch] (hooks/use-reducer reducer
                                                 0)]
    (<>
     (pr-str curr-count)
     (d/button {:on-click #(dispatch :add)}
               "+")
     (d/button {:on-click #(dispatch :minus)}
               "-"))))

(defnc app []
  (let [[state set-state] (hooks/use-state {:name "Helix User"})]
    (<>
     (d/div
      (d/h1 "Welcome!")
      ;; create elements out of components
      ($ greeting {:name (:name state)})

      ;; use reagent component
      #_(r/as-element
         [greeting-reagent {:name (:name state)}])
      (d/input {:value (:name state)
                :on-change #(set-state assoc :name (.. % -target -value))})


      (d/hr)

      ($ reducer-sample)
      ))))

;; start your app with your favorite React renderer
(rdom/render ($ app) (js/document.getElementById "app"))
